package presentatie;

import logica.Controller;
import logica.Danser;
import logica.DataLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * projectv1 : DataEditor
 *
 * @author : Toon Van Havermaet
 * @version : 09/08/2022
 */

public class DataEditor extends JFrame {
    private Controller controller;
    private JPanel mainPanel;

    public DataEditor(Controller dc) {

        this.controller = dc;
        mainPanel = new JPanel();
        JLabel lblVoornaam = new JLabel("voornaam: ");
        JLabel lblFamilienaam = new JLabel("familienaam: ");
        JLabel lblGeboortejaar = new JLabel("geboortejaar: ");
        JLabel lblToevoegenFeedback = new JLabel("");
        JLabel lblBewerkenFeedback = new JLabel("");
        JTextField txtVoornaam = new JTextField();
        JTextField txtFamilienaam = new JTextField();
        JTextField txtGeboortejaar = new JTextField();
        JButton btnVoegDanserToe = new JButton("Voeg danser toe");
        JButton btnBewerkenDanser = new JButton("Bewerk danser");
        JLabel lblDansers = new JLabel("selecteer een danser");

        ArrayList<Danser> danserNamen = controller.getDansers();
        JComboBox cmbDansers = new JComboBox(danserNamen.toArray());

        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new GridLayout(6, 4));

        mainPanel.add(lblVoornaam);
        mainPanel.add(txtVoornaam);
        mainPanel.add(lblFamilienaam);
        mainPanel.add(txtFamilienaam);
        mainPanel.add(lblGeboortejaar);
        mainPanel.add(txtGeboortejaar);
        mainPanel.add(btnVoegDanserToe);
        mainPanel.add(lblToevoegenFeedback);
        mainPanel.add(lblDansers);
        mainPanel.add(cmbDansers);
        mainPanel.add(btnBewerkenDanser);
        mainPanel.add(lblBewerkenFeedback);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Dataeditor");
        this.setSize(500, 500);
        this.setVisible(true);
        btnVoegDanserToe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtVoornaam.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul voornaam in");
                    }
                    if (txtFamilienaam.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul familienaam in");
                    }
                    if (txtGeboortejaar.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul geboortejaar in");
                    }
                    int geboorteJaarAlsInt = Integer.parseInt(txtGeboortejaar.getText());
                    controller.insertDanser(txtVoornaam.getText(), txtFamilienaam.getText(), geboorteJaarAlsInt);
                    lblToevoegenFeedback.setText("danser geregistreerd");
                } catch (SQLException | DataLayerException | IllegalArgumentException ex) {
                    lblToevoegenFeedback.setText(ex.getMessage());
                }
            }
        });


        btnBewerkenDanser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtVoornaam.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul voornaam in");
                    }
                    if (txtFamilienaam.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul familienaam in");
                    }
                    if (txtGeboortejaar.getText().isEmpty()) {
                        throw new IllegalArgumentException("vul geboortejaar in");
                    }
                    int geboorteJaarAlsInt = Integer.parseInt(txtGeboortejaar.getText());
                   String selectedItem = cmbDansers.getSelectedItem().toString();
                   int index = Integer.parseInt(selectedItem.split("-")[0]);
                    controller.updateDanser(index,txtVoornaam.getText(), txtFamilienaam.getText(), geboorteJaarAlsInt);

                    lblToevoegenFeedback.setText("danser bewerkt");
                } catch (SQLException | DataLayerException | IllegalArgumentException ex) {
                    lblToevoegenFeedback.setText(ex.getMessage());
                }
            }
        });



    }


}