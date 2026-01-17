import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    static ArrayList<Contact> contacts = new ArrayList<>();
    static DefaultTableModel model;
    static JTable table;
    static TableRowSorter<DefaultTableModel> sorter;

    public static void main(String[] args) {

        Category friend = new Category(1, "Friend");
        Category family = new Category(2, "Family");
        Category coworker = new Category(3, "Coworker");

        contacts.add(new Contact("Νικόλαος", "Παπαδόπουλος", "2101234567", "nikos.pap@example.gr", "Οδός Ερμού 12, Αθήνα", friend));
        contacts.add(new Contact("Ελένη", "Κωνσταντίνου", "2102345678", "eleni.kon@example.gr", "Λεωφόρος Κηφισίας 45, Αθήνα", family));
        contacts.add(new Contact("Γεώργιος", "Νικολάου", "2310123456", "george.nik@example.gr", "Οδός Τσιμισκή 10, Θεσσαλονίκη", coworker));
        contacts.add(new Contact("Μαρία", "Παπαδοπούλου", "2103456789", "maria.pap@example.gr", "Οδός Σόλωνος 5, Αθήνα", friend));
        contacts.add(new Contact("Δημήτριος", "Αλεξίου", "2610123456", "dimitris.al@example.gr", "Λεωφόρος Αθηνών 22, Πάτρα", coworker));
        contacts.add(new Contact("Κατερίνα", "Βασιλείου", "2104567890", "katerina.vas@example.gr", "Οδός Πατησίων 33, Αθήνα", family));
        contacts.add(new Contact("Αλέξανδρος", "Μανωλάκης", "2311234567", "alex.man@example.gr", "Οδός Τσιμισκή 50, Θεσσαλονίκη", friend));
        contacts.add(new Contact("Σοφία", "Δημητρίου", "2105678901", "sofia.dim@example.gr", "Λεωφόρος Συγγρού 60, Αθήνα", coworker));
        contacts.add(new Contact("Ιωάννης", "Καρράς", "2611234567", "ioannis.k@example.gr", "Οδός Ρήγα Φεραίου 18, Πάτρα", family));
        contacts.add(new Contact("Αναστασία", "Σταυράκη", "2312345678", "anastasia.sta@example.gr", "Οδός Αγίας Σοφίας 12, Θεσσαλονίκη", friend));
        contacts.add(new Contact("Ryan", "Allen", "909090909", "ryan.allen@email.com", "123 Oak Avenue", friend));
        contacts.add(new Contact("Sophia", "Young", "121212121", "sophia.young@email.com", "456 Pine Road", family));
        contacts.add(new Contact("Thomas", "King", "232323232", "thomas.king@email.com", "789 Birch Lane", coworker));
        contacts.add(new Contact("Uma", "Wright", "343434343", "uma.wright@email.com", "321 Cedar Blvd", friend));
        contacts.add(new Contact("Victor", "Scott", "454545454", "victor.scott@email.com", "654 Spruce Street", family));
        contacts.add(new Contact("Wendy", "Green", "565656565", "wendy.green@email.com", "987 Willow Road", coworker));
        contacts.add(new Contact("Xander", "Baker", "676767676", "xander.baker@email.com", "123 Elm Lane", friend));
        contacts.add(new Contact("Yara", "Adams", "787878787", "yara.adams@email.com", "456 Chestnut Blvd", family));
        contacts.add(new Contact("Zane", "Nelson", "898989898", "zane.nelson@email.com", "789 Poplar Street", coworker));
        contacts.add(new Contact("Abby", "Carter", "101112131", "abby.carter@email.com", "321 Magnolia Ave", friend));
        contacts.add(new Contact("Ben", "Mitchell", "141516171", "ben.mitchell@email.com", "654 Aspen Road", coworker));
        contacts.add(new Contact("Clara", "Perez", "181920212", "clara.perez@email.com", "987 Redwood Lane", family));
        contacts.add(new Contact("Dylan", "Roberts", "222324252", "dylan.roberts@email.com", "123 Fir Blvd", friend));
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Contact Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(34, 34, 34));

        String[] columnNames = {"First Name", "Last Name", "Phone", "Email", "Address", "Category"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        table.setBackground(new Color(45, 45, 45));
        table.setForeground(new Color(0, 150, 255));
        table.setRowHeight(28);

        sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        for (Contact c : contacts) {
            addContactToTable(c);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // 🔍 SEARCH BAR
        JTextField searchField = new JTextField(20);
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setForeground(Color.WHITE);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(34, 34, 34));
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            private void filter() {
                String text = searchField.getText();
                if (text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }
        });

        // BUTTONS
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(34, 34, 34));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        addButton.addActionListener(e -> addContactDialog());
        editButton.addActionListener(e -> editContactDialog());
        deleteButton.addActionListener(e -> deleteContact());

        frame.setLayout(new BorderLayout());
        frame.add(searchPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void addContactToTable(Contact c) {
        model.addRow(new Object[]{
                c.getFirstName(), c.getLastName(), c.getPhone(),
                c.getEmail(), c.getAddress(), c.getCategory().getName()
        });
    }

    private static void addContactDialog() {
        JTextField fn = new JTextField();
        JTextField ln = new JTextField();
        JTextField ph = new JTextField();
        JTextField em = new JTextField();
        JTextField ad = new JTextField();
        JComboBox<String> cat = new JComboBox<>(new String[]{"Friend", "Family", "Coworker"});

        Object[] msg = {"First Name:", fn, "Last Name:", ln, "Phone:", ph,
                "Email:", em, "Address:", ad, "Category:", cat};

        if (JOptionPane.showConfirmDialog(null, msg, "Add Contact",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            Category category = new Category(cat.getSelectedIndex() + 1, (String) cat.getSelectedItem());
            Contact c = new Contact(fn.getText(), ln.getText(), ph.getText(),
                    em.getText(), ad.getText(), category);

            contacts.add(c);
            addContactToTable(c);
        }
    }

    private static void editContactDialog() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = table.convertRowIndexToModel(viewRow);
        Contact c = contacts.get(modelRow);

        JTextField fn = new JTextField(c.getFirstName());
        JTextField ln = new JTextField(c.getLastName());
        JTextField ph = new JTextField(c.getPhone());
        JTextField em = new JTextField(c.getEmail());
        JTextField ad = new JTextField(c.getAddress());
        JComboBox<String> cat = new JComboBox<>(new String[]{"Friend", "Family", "Coworker"});
        cat.setSelectedIndex(c.getCategory().getId() - 1);

        Object[] msg = {"First Name:", fn, "Last Name:", ln, "Phone:", ph,
                "Email:", em, "Address:", ad, "Category:", cat};

        if (JOptionPane.showConfirmDialog(null, msg, "Edit Contact",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

            c.setFirstName(fn.getText());
            c.setLastName(ln.getText());
            c.setPhone(ph.getText());
            c.setEmail(em.getText());
            c.setAddress(ad.getText());
            c.setCategory(new Category(cat.getSelectedIndex() + 1, (String) cat.getSelectedItem()));

            model.setValueAt(c.getFirstName(), modelRow, 0);
            model.setValueAt(c.getLastName(), modelRow, 1);
            model.setValueAt(c.getPhone(), modelRow, 2);
            model.setValueAt(c.getEmail(), modelRow, 3);
            model.setValueAt(c.getAddress(), modelRow, 4);
            model.setValueAt(c.getCategory().getName(), modelRow, 5);
        }
    }

    private static void deleteContact() {
        int viewRow = table.getSelectedRow();
        if (viewRow == -1) return;

        int modelRow = table.convertRowIndexToModel(viewRow);

        if (JOptionPane.showConfirmDialog(null, "Delete this contact?",
                "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            contacts.remove(modelRow);
            model.removeRow(modelRow);
        }
    }
}
