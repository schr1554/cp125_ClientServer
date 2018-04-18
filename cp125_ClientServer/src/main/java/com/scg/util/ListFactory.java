package com.scg.util;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.NonBillableAccount;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;

/**
 * Initialize client list files.
 */
public final class ListFactory {
    /** Number of hours in a standard working day. */
    private static final int STD_WORK_DAY = 8;

    /** Some overtime hours. */
    private static final int OT_HOURS = 4;

    /** Index to the first client. */
    private static final int FIRST_CLIENT_NDX = 0;

    /** Index to the second client. */
    private static final int SECOND_CLIENT_NDX = 1;

    /** This class' logger. */
    private static final Logger logger = LoggerFactory.getLogger("InitLists");

    /** The start month for our test cases. */
    private static final Month TEST_MONTH = Month.FEBRUARY;

    /** The first Monday of the test month. */
    private static final int TEST_START_FIRST_WEEK = 27;

    /** The test year. */
    private static final int TEST_YEAR = 2006;
    /**
     * Prevent instantiation.
     */
    private ListFactory() {
    }

    /**
     * Create some time card instances.  Also, creates the consultants the
     * time cards are for.
     *
     * @param clients the list to create the new clients in
     * @param consultants the list to return the new consultants in
     * @param timeCards the to to return the new timeCards in
     */
    public static void populateLists(final List<ClientAccount> clients,
                                     final List<Consultant> consultants,
                                     final List<TimeCard> timeCards) {
        clients.add(new ClientAccount("Acme Industries",
                    new Name("Coyote", "Wiley"),
                    new Address("1616 Index Ct.", "Redmond", StateCode.WA, "98055")));
        clients.add(new ClientAccount("FooBar Enterprises",
                    new Name("Sam", "Yosemite"),
                    new Address("1024 Kilobyte Dr.", "Silicone Gulch", StateCode.CA, "94105")));

        // Create some Consultants
        final Consultant programmer = new Consultant(new Name("Coder", "Carl"));
        final Consultant systemAnalyst = new Consultant(new Name("Architect", "Ann", "S."));
        consultants.add(programmer);
        consultants.add(systemAnalyst);

        LocalDate startDate = LocalDate.of(TEST_YEAR, TEST_MONTH, TEST_START_FIRST_WEEK);

        // Create some TimeCards
        // The first one
        LocalDate currentDay = startDate;
        TimeCard timeCard = new TimeCard(programmer, currentDay);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        logger.trace("Created first TimeCard: ", timeCard.toReportString());
        timeCards.add(timeCard);

        // The second one
        currentDay = startDate.plusWeeks(1);
        timeCard = new TimeCard(programmer, currentDay);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(FIRST_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY + OT_HOURS));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, NonBillableAccount.VACATION,
                Skill.SOFTWARE_ENGINEER, STD_WORK_DAY));
        logger.trace("Created second TimeCard: ", timeCard.toReportString());
        timeCards.add(timeCard);

        // The third one
        currentDay = startDate.plusWeeks(1);
        timeCard = new TimeCard(systemAnalyst, currentDay);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, NonBillableAccount.SICK_LEAVE,
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        logger.trace("Created third TimeCard: ", timeCard.toReportString());
        timeCards.add(timeCard);

        // The forth one
        currentDay = startDate.plusWeeks(2);
        timeCard = new TimeCard(systemAnalyst, currentDay);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay,
                NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay,
                NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        currentDay = currentDay.plusDays(1);
        timeCard.addConsultantTime(new ConsultantTime(currentDay, clients.get(SECOND_CLIENT_NDX),
                Skill.SYSTEM_ARCHITECT, STD_WORK_DAY));
        logger.trace("Created forth TimeCard: ", timeCard.toReportString());
        timeCards.add(timeCard);
    }

    /**
     * Print the time card instances.
     *
     * @param timeCards the time cards to print
     * @param out The output stream; can be System.out or a text file.
     */
    public static void printTimeCards(final List<TimeCard> timeCards, final PrintStream out) {
        for (final TimeCard timeCard : timeCards) {
            out.println(timeCard.toReportString());
        }
    }
}
