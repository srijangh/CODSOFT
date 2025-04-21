import java.util.*;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Intro to Programming", "Learn the basics of Java", 3, "Mon 10-12"));
        courses.add(new Course("MATH201", "Calculus I", "Differential and Integral Calculus", 2, "Tue 14-16"));
        courses.add(new Course("PHY150", "Physics I", "Mechanics and Waves", 2, "Wed 12-14"));

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Student student = new Student(id, name);

        int choice;
        do {
            System.out.println("\n--- Course Registration Menu ---");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\n--- Available Courses ---");
                    for (Course c : courses) {
                        c.showCourseDetails();
                    }
                    break;
                case 2:
                    System.out.print("Enter course code to register: ");
                    String registerCode = scanner.nextLine();
                    Course regCourse = findCourseByCode(courses, registerCode);
                    if (regCourse != null) {
                        student.registerCourse(regCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter course code to drop: ");
                    String dropCode = scanner.nextLine();
                    Course dropCourse = findCourseByCode(courses, dropCode);
                    if (dropCourse != null) {
                        student.dropCourse(dropCourse);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    student.showRegisteredCourses();
                    break;
                case 5:
                    System.out.println("Thank you! Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    public static Course findCourseByCode(List<Course> courses, String code) {
        for (Course c : courses) {
            if (c.code.equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<Student> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean isAvailable() {
        return registeredStudents.size() < capacity;
    }

    public void registerStudent(Student student) {
        if (isAvailable() && !registeredStudents.contains(student)) {
            registeredStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    public void showCourseDetails() {
        System.out.println(code + " - " + title + " (" + schedule + ")");
        System.out.println("Description: " + description);
        System.out.println("Available Slots: " + (capacity - registeredStudents.size()));
        System.out.println();
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.isAvailable()) {
            registeredCourses.add(course);
            course.registerStudent(this);
            System.out.println("Successfully registered for: " + course.title);
        } else {
            System.out.println("Cannot register. Course might be full or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
            System.out.println("Successfully dropped: " + course.title);
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    public void showRegisteredCourses() {
        System.out.println("Courses registered by " + name + ":");
        for (Course c : registeredCourses) {
            System.out.println("- " + c.code + ": " + c.title);
        }
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        }
    }
}
