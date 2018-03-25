package pl.zax37.javatask5;

import com.external.PaymentsService;
import com.internal.DiscountCalculator;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    private final static DiscountCalculator discountCalculator = new DiscountCalculator();
    private final static PaymentsService paymentsService = new PaymentsService();

    public static void main(String[] args) throws ParseException {
        logger.trace("Application started: Main.main("+ Arrays.toString(args)+")");
        Options options = new Options();
        options.addRequiredOption("price", null, true, "ticket price");
        options.addRequiredOption("age", null, true, "client age");
        options.addRequiredOption("clientId", null, true, "client id");
        options.addRequiredOption("companyId", null, true, "company id");
        CommandLineParser parser = new DefaultParser();
        try {
            logger.trace("Trying to parse delivered arguments.");
            CommandLine cmd = parser.parse(options, args);
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(cmd.getOptionValue("price")));
            Integer customerAge = Integer.parseInt(cmd.getOptionValue("age"));
            Integer customerId = Integer.parseInt(cmd.getOptionValue("clientId"));
            Integer companyId = Integer.parseInt(cmd.getOptionValue("companyId"));
            BigDecimal finalPrice = price.subtract(discountCalculator.calculateDiscount(price, customerAge));
            logger.info("Making payment of $"+finalPrice+" for customerId "+customerId+" and companyId "+companyId+".");
            if (paymentsService.makePayment(customerId, companyId, finalPrice)) {
                logger.info("Payment successfull.");
            } else {
                logger.info("Payment could not be finalized.");
                System.out.println("Could not finalize the payment.");
            }
        } catch (MissingOptionException e) {
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("pass all required options:", options);
            logger.error("Wrong arguments were passed. Missing options: "+e.getMissingOptions());
        }
    }
}