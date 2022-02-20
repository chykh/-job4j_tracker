package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.time.format.DateTimeFormatter;
public class StartUITest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"0", "Item name", "1"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(out), new Exit()};
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        String replacedName = "New item name";
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), replacedName, "1"});
        UserAction[] actions = {new ReplaceAction(out), new Exit()};
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {new DeleteAction(out), new Exit()};
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(new String[]{"0"});
        Tracker tracker = new Tracker();
        UserAction[] actions = {new Exit()};
        new StartUI(out).init(in, tracker, actions);

        assertThat(out.toString(), is("Menu:" + System.lineSeparator()
                + "0. Exit" + System.lineSeparator()));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(new String[] {"0",
                String.valueOf(one.getId()), replaceName, "1"});
        UserAction[] actions = new UserAction[]{new ReplaceAction(out), new Exit()};
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(new String[] {"5", "0"});
        UserAction[] actions = new UserAction[]{new Exit()};
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Exit" + ln
                + "Wrong input, you can select: 0 .. 0" + ln
                + "Menu:" + ln
                + "0. Exit" + ln));
    }

    @Test
    public void whenFindbyId() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        UserAction[] actions = {new FindByIdAction(out), new Exit()};
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                           + "0. Find item by id" + ln
                           + "1. Exit" + ln
                           + "=== Find item by id ===" + ln
                           + "Item{id = 1, name = 'test', created = "
                           + item.getLocalDateTime().format(FORMATTER) + "}" + ln
                           + "Menu:" + ln
                           + "0. Find item by id" + ln
                           + "1. Exit" + ln));
    }

    @Test
    public void whenFindbyName() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        UserAction[] actions = {new FindByNameAction(out), new Exit()};
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getName()), "1"});
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Find items by name" + ln
                + "1. Exit" + ln
                + "=== Find items by name ===" + ln
                + "Item{id = 1, name = 'test', created = "
                + item.getLocalDateTime().format(FORMATTER) + "}" + ln
                + "Menu:" + ln
                + "0. Find items by name" + ln
                + "1. Exit" + ln));
    }

    @Test
    public void whenFindbyAllItems() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Item item2 = tracker.add(new Item("test2"));
        UserAction[] actions = {new ShowItemAction(out), new Exit()};
        Input in = new StubInput(new String[]{"0", "1"});
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Menu:" + ln
                + "0. Show all items" + ln
                + "1. Exit" + ln
                + "=== Show all items ===" + ln
                + "Item{id = 1, name = 'test', created = "
                + item.getLocalDateTime().format(FORMATTER) + "}" + ln
                + "Item{id = 2, name = 'test2', created = "
                + item2.getLocalDateTime().format(FORMATTER) + "}" + ln
                + "Menu:" + ln
                + "0. Show all items" + ln
                + "1. Exit" + ln));
    }

}