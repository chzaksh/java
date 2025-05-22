import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

/**
 * CalendarController - This class controls the calendar UI, handles user interaction,
 * and manages appointment data for each day using a simple in-memory map.
 */
public class CalendarController {

    @FXML private ComboBox<Integer> yearCombo;
    @FXML private ComboBox<String> monthCombo;
    @FXML private GridPane calendarGrid;

    private final Map<LocalDate, List<String>> appointments = new HashMap<>();

    // Called automatically when FXML loads
    @FXML
    public void initialize() {
        for (int y = 2020; y <= 2030; y++) {
            yearCombo.getItems().add(y);
        }
        yearCombo.setValue(LocalDate.now().getYear());

        monthCombo.getItems().addAll(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        monthCombo.setValue(monthCombo.getItems().get(LocalDate.now().getMonthValue() - 1));

        showCalendar();
    }

    // Build the calendar grid
    @FXML
    public void showCalendar() {
        calendarGrid.getChildren().clear();
        int year = yearCombo.getValue();
        int month = monthCombo.getSelectionModel().getSelectedIndex() + 1;
        YearMonth ym = YearMonth.of(year, month);
        LocalDate firstDay = ym.atDay(1);
        int daysInMonth = ym.lengthOfMonth();
        int startDay = firstDay.getDayOfWeek().getValue() % 7; // Sunday = 0

        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < days.length; i++) {
            Label label = new Label(days[i]);
            label.setStyle("-fx-font-weight: bold;");
            calendarGrid.add(label, i, 0);
        }

        int row = 1;
        int col = startDay;

        for (int day = 1; day <= daysInMonth; day++) {
            Button btn = new Button(String.valueOf(day));
            btn.setPrefWidth(40);
            btn.setPrefHeight(30);

            int d = day;
            btn.setOnAction(e -> handleDayClick(year, month, d));
            calendarGrid.add(btn, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }

    // Opens a dialog window to view, add, edit, or delete appointments for a specific day.
    private void handleDayClick(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        List<String> dayAppointments = new ArrayList<>(appointments.getOrDefault(date, new ArrayList<>()));

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Appointments for " + date);        

        ListView<String> listView = createListView(dayAppointments, date);
        HBox buttons = createButtons(listView, date);

        VBox content = new VBox(10,
                new Label("Double-click to edit. Select an item and click Delete to remove."),
                listView,
                buttons
        );
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait();

        appointments.put(date, new ArrayList<>(listView.getItems()));
    }

    // Creates a ListView displaying all appointments and handles double-click editing.
    private ListView<String> createListView(List<String> items, LocalDate date) {
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(items);

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    String current = listView.getItems().get(selectedIndex);
                    Optional<String> result = showMultiLineInputDialog("Edit Appointment", "Edit selected appointment:", current);
                    result.ifPresent(newText -> listView.getItems().set(selectedIndex, newText));
                }
            }
        });

        return listView;
    }

    // Creates buttons for adding and deleting appointments, and connects them to the ListView.
    private HBox createButtons(ListView<String> listView, LocalDate date) {
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> {
            Optional<String> result = showMultiLineInputDialog("New Appointment", "Enter new appointment (Use Ctrl+Enter to add a new line):", "");
            result.ifPresent(text -> listView.getItems().add(text));
        });

        deleteButton.setOnAction(e -> {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                listView.getItems().remove(selectedIndex);
            }
        });

        HBox buttonBox = new HBox(10, addButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    // Shows a custom dialog with TextArea for multi-line appointment input
    private Optional<String> showMultiLineInputDialog(String title, String header, String initialText) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        ButtonType okButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        TextArea textArea = new TextArea(initialText);
        textArea.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    if (event.isControlDown()) {
                        textArea.appendText(System.lineSeparator());
                        event.consume();
                    }
                    break;
                default:
                    break;
            }
        });
        textArea.setWrapText(true);
        textArea.setPrefColumnCount(25);
        textArea.setPrefRowCount(5);

        dialog.getDialogPane().setContent(textArea);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return textArea.getText().trim();
            }
            return null;
        });

        return dialog.showAndWait();
    }
} 
