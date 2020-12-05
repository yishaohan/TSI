package com.ysh.talentshowintro.controller;

import com.paypal.http.HttpResponse;
import com.paypal.orders.*;
import com.ysh.talentshowintro.model.*;
import com.ysh.talentshowintro.paypal.Credentials;
import com.ysh.talentshowintro.service.*;
import com.ysh.talentshowintro.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class Router {
    final TicketService ticketService;
    final ContestantService contestantService;
    final EmailService emailService;
    final OrderService orderService;
    final MessageService messageService;
    final MailQueueService mailQueueService;
    final AppParamService appParamService;

    public Router(TicketService ticketService, ContestantService contestantService, EmailService emailService, OrderService orderService, MessageService messageService, MailQueueService mailQueueService, AppParamService appParamService) {
        this.ticketService = ticketService;
        this.contestantService = contestantService;
        this.emailService = emailService;
        this.orderService = orderService;
        this.messageService = messageService;
        this.mailQueueService = mailQueueService;
        this.appParamService = appParamService;
    }

    @PostMapping("/vote")
    public void getVote(HttpServletRequest req, HttpServletResponse resp) {
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String name = req.getParameter("vote" + i);
            if (name != null) {
                names.add(name);
            }
        }
        String email = req.getParameter("email1");
        if (names.size() > 2) {
            try {
                resp.sendRedirect("/?error=vote greater than three !");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (email == null || "".equals(email) || !email.contains("@")) {
            try {
                resp.sendRedirect("/?error=Email format is incorrect !");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (!emailService.getAllEmails().contains(email)) {
            try {
                resp.sendRedirect("/?error=email not found !");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (emailService.isVoted(email)) {
            try {
                resp.sendRedirect("/?error=You already voted !");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String sdt = appParamService.getAppParamByKey("voteStartDateTime");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Vancouver"));
        Timestamp startDateTime = null;
        try {
            startDateTime = new Timestamp(df.parse(sdt).getTime());
        } catch (ParseException e) {
        }
        Timestamp currentDateTime = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("America/Vancouver")).getTime().getTime());
        long second = (startDateTime.getTime() - currentDateTime.getTime()) / 1000;
        if (second > 0) {
            try {
                resp.sendRedirect("/?error=Voting has not started yet, please try again later !");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        emailService.vote(email);
        for (String s : names) {
            contestantService.voteByName(s);
        }
        try {
            resp.sendRedirect("/?message=Thanks for voting !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        String sdt = appParamService.getAppParamByKey("voteStartDateTime");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Vancouver"));
        Timestamp startDateTime = null;
        try {
            startDateTime = new Timestamp(df.parse(sdt).getTime());
        } catch (ParseException e) {
        }
        Timestamp currentDateTime = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("America/Vancouver")).getTime().getTime());
        long second = (startDateTime.getTime() - currentDateTime.getTime()) / 1000;
        mv.addObject("second", second);
        return mv;
    }

    @GetMapping("/admin")
    public ModelAndView getAdmin(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("admin");
        List<Contestant> contestants = contestantService.getContestants();
        mv.addObject("contestants", contestants);
        mv.addObject("sum", contestantService.getNumOfBallots());
        mv.addObject("num", ticketService.getAllTickets().size());
        String voteStartDateTime = appParamService.getAppParamByKey("voteStartDateTime");
        String date = voteStartDateTime.split(" ")[0];
        String time = voteStartDateTime.split(" ")[1];
        time = time.substring(0, 5);
        mv.addObject("date", date);
        mv.addObject("time", time);
        String enableVerificationCode = appParamService.getAppParamByKey("enableVerificationCode");
        if (enableVerificationCode.equals("false")) {
            mv.addObject("enableVerificationCode", false);
        } else if (enableVerificationCode.equals("true")) {
            mv.addObject("enableVerificationCode", true);
        }
        return mv;
    }

    @GetMapping("/captureOrder")
    @ResponseBody
    public String captureOrder(HttpServletRequest req) {
        String reqID = req.getParameter("id");
        String email = req.getParameter("email");
        if (reqID == null || "".equals(reqID) || email == null || "".equals(email)) {
            return "Error";
        }
        CustomOrder customOrder = new CustomOrder();
        OrdersGetRequest request = new OrdersGetRequest(reqID);
        HttpResponse<Order> response = null;
        try {
            response = Credentials.paypalClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            return "Error";
        }
        Order order = response.result();
        if (order == null) {
            return "Error";
        }
        customOrder.setOrderID(StringUtils.isNull(order.id()));
        customOrder.setIntent(StringUtils.isNull(order.checkoutPaymentIntent()));
        customOrder.setOrderStatus(StringUtils.isNull(order.status()));
        String ut = StringUtils.isNull(order.updateTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Timestamp updateDateTime = null;
        try {
            updateDateTime = new Timestamp(df.parse(ut).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customOrder.setUpdateDateTime(StringUtils.isNull(updateDateTime));
        final List<PurchaseUnit> purchaseUnits = order.purchaseUnits();
        //为简化开发,只获取第一个商家的信息
        PurchaseUnit purchaseUnit = purchaseUnits.get(0);
        if (purchaseUnit == null) {
            return "Error";
        }
        final Payee payee = purchaseUnit.payee();
        if (payee == null) {
            customOrder.setPayeeClientID("");
            customOrder.setPayeeEmail("");
            customOrder.setPayeeMerchantID("");
        } else {
            customOrder.setPayeeClientID(StringUtils.isNull(payee.clientId()));
            customOrder.setPayeeEmail(StringUtils.isNull(payee.email()));
            customOrder.setPayeeMerchantID(StringUtils.isNull(payee.merchantId()));
        }
        final PayeeDisplayable payeeDisplayable = payee.payeeDisplayable();
        if (payeeDisplayable == null) {
            customOrder.setPayeeDisplayBrandName("");
            customOrder.setPayeeDisplayBusinessPhone("");
            customOrder.setPayeeDisplayEmail("");
        } else {
            customOrder.setPayeeDisplayBrandName(StringUtils.isNull(payeeDisplayable.brandName()));
            final Phone phone = payeeDisplayable.businessPhone();
            String tmp = "" + phone.countryCallingCode() + " " + phone.nationalNumber() + " " + phone.extensionNumber();
            customOrder.setPayeeDisplayBusinessPhone(StringUtils.isNull(tmp));
            customOrder.setPayeeDisplayEmail(StringUtils.isNull(payeeDisplayable.email()));
        }
        final AmountWithBreakdown amountWithBreakdown = purchaseUnit.amountWithBreakdown();
        if (amountWithBreakdown == null) {
            customOrder.setAmountValue("");
            customOrder.setAmountCurrencyCode("");
        } else {
            customOrder.setAmountValue(StringUtils.isNull(amountWithBreakdown.value()));
            customOrder.setAmountCurrencyCode(StringUtils.isNull(amountWithBreakdown.currencyCode()));
        }
//            final ShippingDetail shippingDetail = purchaseUnit.shippingDetail();
//            final AddressPortable addressPortable = shippingDetail.addressPortable();
//            addressPortable.countryCode();
//            addressPortable.postalCode();
//            addressPortable.addressLine1();
//            addressPortable.addressLine2();
//            addressPortable.addressLine3();
//            addressPortable.adminArea1();
//            addressPortable.adminArea2();
//            addressPortable.adminArea3();
//            addressPortable.adminArea4();
//            final Name name = shippingDetail.name();
//            name.fullName();
//            name.surname();
//            name.givenName();
//            final PaymentCollection payments = purchaseUnit.payments();
//            final List<Capture> captures = payments.captures();
//            for (final Capture capture : captures) {
//                capture.id();
//                capture.status();
//                capture.finalCapture();
//                final Money amount = capture.amount();
//                amount.value();
//                amount.currencyCode();
//                capture.createTime();
//                capture.updateTime();
//                final List<LinkDescription> links = capture.links();
//                for (final LinkDescription link : links) {
//                    link.method();
//                    link.rel();
//                    link.href();
//                }
        //为简化开发,只计算合计的数量
        int total = 0;
        final List<Item> items = purchaseUnit.items();
        for (final Item item : items) {
            item.name();
            total += Integer.valueOf(item.quantity());
            final Money money = item.unitAmount();
            money.value();
            money.currencyCode();
        }
        customOrder.setQuantity(total);
//        }
//        final List<LinkDescription> links = order.links();
//        for (final LinkDescription link : links) {
//            link.method();
//            link.rel();
//            link.href();
//        }
        final Payer payer = order.payer();
        if (payer == null) {
            return "Error";
        } else {
            customOrder.setPayerID(StringUtils.isNull(payer.payerId()));
            final Name name = payer.name();
            if (name == null) {
                customOrder.setPayerFullName("");
                customOrder.setPayerGivenName("");
                customOrder.setPayerSurname("");
            } else {
                customOrder.setPayerFullName(StringUtils.isNull(name.fullName()));
                customOrder.setPayerGivenName(StringUtils.isNull(name.givenName()));
                customOrder.setPayerSurname(StringUtils.isNull(name.surname()));
            }
            final PhoneWithType phoneWithType = payer.phoneWithType();
            if (phoneWithType == null) {
                customOrder.setPayerPhoneType("");
                customOrder.setPayerPhoneNumber("");
            } else {
                customOrder.setPayerPhoneType(StringUtils.isNull(phoneWithType.phoneType()));
                final Phone phone1 = phoneWithType.phoneNumber();
                String tmp = "" + phone1.countryCallingCode() + " " + phone1.nationalNumber() + " " + phone1.extensionNumber();
                customOrder.setPayerPhoneNumber(StringUtils.isNull(tmp));
            }
            customOrder.setPayerEmail(StringUtils.isNull(payer.email()));
            final AddressPortable addressPortable = payer.addressPortable();
            if (addressPortable == null) {
                customOrder.setPayerCountryCode("");
                customOrder.setPayerPostalCode("");
                customOrder.setPayerAddressLine1("");
                customOrder.setPayerAddressLine2("");
                customOrder.setPayerAddressLine3("");
                customOrder.setPayerAdminArea1("");
                customOrder.setPayerAdminArea2("");
                customOrder.setPayerAdminArea3("");
                customOrder.setPayerAdminArea4("");
            } else {
                customOrder.setPayerCountryCode(StringUtils.isNull(addressPortable.countryCode()));
                customOrder.setPayerPostalCode(StringUtils.isNull(addressPortable.postalCode()));
                customOrder.setPayerAddressLine1(StringUtils.isNull(addressPortable.addressLine1()));
                customOrder.setPayerAddressLine2(StringUtils.isNull(addressPortable.addressLine2()));
                customOrder.setPayerAddressLine3(StringUtils.isNull(addressPortable.addressLine3()));
                customOrder.setPayerAdminArea1(StringUtils.isNull(addressPortable.adminArea1()));
                customOrder.setPayerAdminArea2(StringUtils.isNull(addressPortable.adminArea2()));
                customOrder.setPayerAdminArea3(StringUtils.isNull(addressPortable.adminArea3()));
                customOrder.setPayerAdminArea4(StringUtils.isNull(addressPortable.adminArea4()));
            }
        }
//        order.setOrderID(StringUtils.isNull(req.getParameter("id")));
//        order.setIntent(StringUtils.isNull(req.getParameter("intent")));
//        order.setStatus(StringUtils.isNull(req.getParameter("status")));
//        String cdt = StringUtils.isNull(req.getParameter("createTime"));
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Timestamp createDateTime = null;
//        try {
//            createDateTime = new Timestamp(df.parse(cdt).getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        order.setCreateDateTime(createDateTime);
//        String ut = StringUtils.isNull(req.getParameter("updateTime"));
//        Timestamp updateDateTime = null;
//        try {
//            updateDateTime = new Timestamp(df.parse(ut).getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        order.setUpdateDateTime(updateDateTime);
//        order.setPayerID(StringUtils.isNull(req.getParameter("payerID")));
//        order.setPayerGivenName(StringUtils.isNull(req.getParameter("payerGivenName")));
//        order.setPayerSurname(StringUtils.isNull(req.getParameter("payerSurname")));
//        order.setPayerEmail(StringUtils.isNull(req.getParameter("payerEmail")));
        orderService.save(customOrder);

        int quantity = customOrder.getQuantity();
        List<Ticket> tickets = (List<Ticket>) req.getSession().getAttribute("tickets");
        if (tickets == null) {
            System.out.println("tickets is null");
            tickets = new ArrayList<Ticket>();
            req.getSession().setAttribute("tickets", tickets);
        }
        while (quantity-- > 0) {
            Ticket ticket = new Ticket();
            ticket.setOwner(StringUtils.isNull(email));
            ticket.setOrderID(StringUtils.isNull(customOrder.getOrderID()));
            Random random = new Random();
            String number = Integer.toString(random.nextInt(1000000) + 1000000);
            number = number.substring(1);
            ticket.setNumber(StringUtils.isNull(number));
            Timestamp createDateTime = new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime());
            ticket.setCreateDateTime(createDateTime);
            ticket.setShow(false);
            ticketService.save(ticket);
            tickets.add(ticket);
        }
        mailQueueService.addMail(StringUtils.isNull(email), null, null, "彩票回执", "sendVoteMail", tickets, null);
        return "Success";
    }

    @GetMapping("/orderCancel")
    public void orderCancel(HttpServletRequest req, HttpServletResponse resp) {
        Message message = new Message();
        message.setCreateDateTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()));
        message.setSubject("Canceled Order !");
        message.setMessage(StringUtils.isNull(req.getParameter("message")));
        messageService.save(message);
        mailQueueService.addMail("henryyi2005@gmail.com", null, null, "已取消的订单", "sendWarnMail", null, message);
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/orderError")
    public void orderError(HttpServletRequest req, HttpServletResponse resp) {
        Message message = new Message();
        message.setCreateDateTime(new Timestamp(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime()));
        message.setSubject("Wrong Order !");
        message.setMessage(StringUtils.isNull(req.getParameter("message")));
        messageService.save(message);
        mailQueueService.addMail("henryyi2005@gmail.com", null, null, "错误订单", "sendWarnMail", null, message);
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/admin/ticketDraw")
    public ModelAndView ticketDraw(HttpServletResponse resp) {
        ModelAndView mv = new ModelAndView("ticketDraw");
        int count = ticketService.getAllTicketsCount();
        int i = 0;
        Ticket ticket;
        do {
            ticket = ticketService.getTicketByID(new Random().nextInt(count));
        } while (ticket == null && i++ < 100);
        mv.addObject("winner", ticket.getNumber());
        return mv;
    }

    @PostMapping("/admin")
    public void setAppParam(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter("startDate");
        String time = request.getParameter("startTime");
        String verify = request.getParameter("verification");
        System.out.println(verify);
        if (date == null || "".equals(date)) {
            try {
                response.sendRedirect("/admin?paramError=Please select a date !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (time == null || "".equals(time)) {
            try {
                response.sendRedirect("/admin?paramError=Please select a time !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String sdt = date + " " + time;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("America/Vancouver"));
        Timestamp startDateTime = null;
        try {
            startDateTime = new Timestamp(df.parse(sdt).getTime());
        } catch (ParseException e) {
            try {
                response.sendRedirect("/admin?paramError=Date or Time selection error !");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        appParamService.setAppParamByKey("voteStartDateTime", sdt);
        if (verify == null || "".equals(verify)) {
            verify = "false";
        } else if ("on".equals(verify)) {
            verify = "true";
        }
        appParamService.setAppParamByKey("enableVerificationCode", verify);
        try {
            response.sendRedirect("/admin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

