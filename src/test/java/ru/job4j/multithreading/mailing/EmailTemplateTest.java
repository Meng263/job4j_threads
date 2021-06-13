package ru.job4j.multithreading.mailing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTemplateTest {
    @Test
    public void subjectTest() {
        User jorra = new User("Jorra", "jorr@mail.ru");
        String result = new EmailTemplate().getSubject(jorra);
                assertEquals(result, "Notification Jorra to email jorr@mail.ru.");
    }

    @Test
    public void bodyTest() {
        User jorra = new User("Jorra", "jorr@mail.ru");
        String result = new EmailTemplate().getBody(jorra);
        assertEquals(result, "Add a new event to Jorra");
    }
}