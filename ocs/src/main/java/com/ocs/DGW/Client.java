package com.ocs.DGW;

import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.ocs.DataAccess.HibernateUtil;

public class Client {
    // ...

    /**
     * Generates data and returns it as a string.
     */
    public String generateData() {
        try {
            // Initialize Hibernate session factory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory == null) {
                throw new Exception("Unable to initialize session factory.");
            }

            // Create Hibernate session and query for MSISDNs
            Session session = sessionFactory.openSession();
            String hqlMsisdn = "SELECT s.msisdn FROM Subscriber s";
            Query<String> queryMsisdn = session.createQuery(hqlMsisdn, String.class);
            List<String> msisdnList = queryMsisdn.list();

            // Rest of the code remains the same...
            // Initialize generators for other fields
            OpNumberGenerator opNumberGenerator = new OpNumberGenerator();
            ServiceGenerator serviceGenerator = new ServiceGenerator();
            LocationGenerator locationGenerator = new LocationGenerator();
            AmountGenerator amountGenerator = new AmountGenerator();

            Random rand = new Random();

            // Generate random MSISDN and OP number
            String msisdn = getRandomElement(msisdnList, rand);
            String opNumber = getOPNumber(opNumberGenerator, serviceGenerator, msisdn, rand);

            // Generate other fields based on random MSISDN and service
            String service = serviceGenerator.getService();
            String location = locationGenerator.getLocation();
            int amount = amountGenerator.getAmount(service);

            // Return the generated fields as a string
            return msisdn + " " + opNumber + " " + service + " " + location + " " + amount + " ";

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return "";
        }
    }

    // ...

    // ...

    /**
     * Returns a random element from the given list using the given random number
     * generator.
     */
    private static <T> T getRandomElement(List<T> list, Random rand) {
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Returns a random OP number for the given service and MSISDN using the given
     * generators and random number generator.
     * The OP number is guaranteed to be different from the given MSISDN.
     */
    private static String getOPNumber(OpNumberGenerator opNumberGenerator, ServiceGenerator serviceGenerator,
            String msisdn, Random rand) {
        String service = serviceGenerator.getService();
        String opNumber = opNumberGenerator.getOPNumber(service, msisdn);
        while (opNumber.equals(msisdn)) {
            opNumber = opNumberGenerator.getOPNumber(service, msisdn);
        }
        return opNumber;
    }
}
