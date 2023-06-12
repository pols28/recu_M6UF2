package Application;
import org.hibernate.Session;

import Model.Departments;
import Model.Employees;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			System.out.println("===========================================");
			System.out.println("CONSULTA EMPLEATS");
			System.out.println("===========================================");

			System.out.print("Introduir el nom de departament: ");
			String departmentName = scanner.nextLine();

			Query<Departments> departmentQuery = session.createQuery("FROM Departments WHERE deptName = :name", Departments.class);
			departmentQuery.setParameter("name", departmentName);
			Departments department = departmentQuery.uniqueResult();

			if (department != null) {
				Query<Employees> employeesQuery = session.createQuery("FROM Employees WHERE department = :department", Employees.class);
				employeesQuery.setParameter("department", department);
				List<Employees> employees = employeesQuery.list();

				System.out.println();
				System.out.println("Emp_no\tNom\tCognoms\t\tData_naix\tRol\tSalari");
				System.out.println("======\t====\t=======\t========\t===\t=====");

				for (Employees employee : employees) {
					System.out.printf("%d\t%s\t%s\t%s\t%s\t%.2f\n",
							employee.getEmpNo(),
							employee.getFirstName(),
							employee.getLastName(),
							employee.getBirth(),
							employee.getRole(),
							employee.getSalary());
				}
			} else {
				System.out.println("No s'ha trobat cap departament amb aquest nom.");
			}

			session.getTransaction().commit();
		}
	}
}
