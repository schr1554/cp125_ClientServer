package com.scg.persistent;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.Invoice;
import com.scg.domain.InvoiceLineItem;
import com.scg.domain.Skill;
import com.scg.domain.TimeCard;
import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

public class DbServerTest {
	/*
	 * DbServer server = new
	 * DbServer("jdbc:derby://localhost:1527/memory:scgDb", "student",
	 * "student"); Name name = new Name("Schroeder", "Alex", "James"); Address
	 * address = new Address("1234 Fake Street", "City", StateCode.CO, "1225");
	 * ClientAccount client = new ClientAccount("Busines Name", name, address);
	 * 
	 * LocalDate today = LocalDate.now();
	 * 
	 * Consultant consultant = new Consultant(name); int hours = 8;
	 * 
	 * ConsultantTime cTime = new ConsultantTime(today, client,
	 * Skill.PROJECT_MANAGER, 8);
	 * 
	 * InvoiceLineItem lineItem = new InvoiceLineItem(today, consultant,
	 * Skill.valueOf("PROJECT_MANAGER"), Integer.valueOf(hours));
	 * 
	 * TimeCard timeCard = new TimeCard(consultant, today);
	 * 
	 * @Test public void addClientTest() {
	 * 
	 * String db = "jdbc:mysql://localhost/EmployeeDB"; String user = "student";
	 * String pass = "student";
	 * 
	 * DbServer dbServer = new DbServer(db, user, pass);
	 * 
	 * try { dbServer.addClient(client); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } List<ClientAccount>
	 * clientList = new ArrayList<ClientAccount>(); try { clientList =
	 * dbServer.getClients(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * clientList.forEach(items -> { assertEquals(items, client); });
	 * 
	 * }
	 * 
	 * @Test public void getClientsTest() {
	 * 
	 * String db = "jdbc:mysql://localhost/EmployeeDB"; String user = "student";
	 * String pass = "student";
	 * 
	 * DbServer dbServer = new DbServer(db, user, pass);
	 * 
	 * try { dbServer.addClient(client); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } List<ClientAccount>
	 * clientList = new ArrayList<ClientAccount>(); try { clientList =
	 * dbServer.getClients(); } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * clientList.forEach(items -> { assertEquals(items, client); });
	 * 
	 * }
	 * 
	 * @Test public void addConsultantTest() {
	 * 
	 * String db = "jdbc:mysql://localhost/EmployeeDB"; String user = "student";
	 * String pass = "student";
	 * 
	 * DbServer dbServer = new DbServer(db, user, pass);
	 * 
	 * try { dbServer.addConsultant(consultant); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } List<Consultant>
	 * consultantList = new ArrayList<Consultant>(); try { consultantList =
	 * dbServer.getConsultants(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * consultantList.forEach(items -> { assertEquals(items, consultant); });
	 * 
	 * }
	 * 
	 * @Test public void getConsultantTest() {
	 * 
	 * String db = "jdbc:mysql://localhost/EmployeeDB"; String user = "student";
	 * String pass = "student";
	 * 
	 * DbServer dbServer = new DbServer(db, user, pass);
	 * 
	 * try { dbServer.addConsultant(consultant); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } List<Consultant>
	 * consultantList = new ArrayList<Consultant>(); try { consultantList =
	 * dbServer.getConsultants(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * consultantList.forEach(items -> { assertEquals(items, consultant); });
	 * 
	 * }
	 * 
	 * @Test public void addTimeCardTest() {
	 * 
	 * String db = "jdbc:mysql://localhost/EmployeeDB"; String user = "student";
	 * String pass = "student";
	 * 
	 * DbServer dbServer = new DbServer(db, user, pass);
	 * timeCard.addConsultantTime(cTime);
	 * 
	 * Invoice invoice = null;
	 * 
	 * try { dbServer.addTimeCard(timeCard); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } try { invoice =
	 * dbServer.getInvoice(client, Month.MARCH, 2017); } catch (SQLException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * assertNotNull(invoice);
	 * 
	 * }
	 */
}
