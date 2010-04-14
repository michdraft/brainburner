package gui;

import helper.Messages;
import java.util.ArrayList;

public class LearnFrame extends Frame {

	private ArrayList<String[]> datasets;
	private int counter;
	
	public LearnFrame(ArrayList<String[]> datasets) {
		initComponents();

		this.datasets = datasets;
		this.counter = 0;

		this.showFirstQuestion();
	}


	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_headline = new javax.swing.JLabel();
        lbl_question = new javax.swing.JLabel();
        txt_question = new javax.swing.JTextField();
        lbl_answer = new javax.swing.JLabel();
        txt_answer = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        lbl_headline.setFont(new java.awt.Font("Dialog", 1, 18));
        lbl_headline.setText("Trainer");

        lbl_question.setText("Question:");

        txt_question.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_question.setEnabled(false);

        lbl_answer.setText("Answer:");

        jButton1.setText("confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbl_headline)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbl_answer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_question, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_question)
                                .addComponent(txt_answer, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_headline)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_question)
                    .addComponent(txt_question, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_answer)
                    .addComponent(txt_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		if(this.counter < this.datasets.size()) {
			if(this.checkIfAnswerIsEmpty()) {
				this.counter++;
				if(this.counter != this.datasets.size())
				{
					txt_question.setText(this.datasets.get(this.counter)[0]);
					txt_answer.setText("");
				} else {
					// Übung fertig! Statistik anzeigen!
				}
			} else {
				Messages.showInfo("Don't forget entering an answer!");
			}
		}
	}//GEN-LAST:event_jButton1ActionPerformed
	@Override
	public void toggleVisibility() {
	    super.toggleVisibility();
	}

	private void showFirstQuestion() {
		txt_question.setText(this.datasets.get(0)[0]);
	}

	private boolean checkIfAnswerIsEmpty() {
		if(txt_answer.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lbl_answer;
    private javax.swing.JLabel lbl_headline;
    private javax.swing.JLabel lbl_question;
    private javax.swing.JTextField txt_answer;
    private javax.swing.JTextField txt_question;
    // End of variables declaration//GEN-END:variables

}
