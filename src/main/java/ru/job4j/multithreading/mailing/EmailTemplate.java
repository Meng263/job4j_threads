package ru.job4j.multithreading.mailing;

public class EmailTemplate {

    public String getSubject(User user) {
        return String.format("Notification %s to email %s.", user.getUserName(), user.getEmail());
    }

    public String getBody(User user) {
        return String.format("Add a new event to %s", user.getUserName());
    }

}
