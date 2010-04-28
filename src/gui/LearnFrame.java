package gui;

import data.DBConnection;
import data.Statistics;
import data.objects.ExerciseArea;
import data.objects.Statistic;
import data.objects.User;
import helper.Helpers;
import helper.Messages;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class LearnFrame extends Frame {

	private ArrayList<String[]> datasets;
	private ArrayList<String[]> wrong_datasets;
	private int idx;
	private User user;
	private ExerciseArea exerciseareaid;
	private double percent;
	private int words_right, words_wrong;
	private DBConnection connection;
	
	public LearnFrame(DBConnection connection, ArrayList<String[]> datasets,
		User user, ExerciseArea exerciseareaid) {
		initComponents();

		this.user = user;
		this.exerciseareaid = exerciseareaid;
		this.percent = 0;
		this.words_right = 0;
		this.words_wrong = 0;
		this.connection = connection;
		this.datasets = datasets;
		this.idx = 0;
		this.wrong_datasets = new ArrayList<String[]>();

		this.getRootPane().setDefaultButton(jButton1);
		this.askQuestion();
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

                jButton1.setText("Confirm");
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
		checkAnswer(this.txt_answer.getText().trim());
		askQuestion();
	}//GEN-LAST:event_jButton1ActionPerformed
	@Override
	public void toggleVisibility() {
	    super.toggleVisibility();
	}

	private boolean checkIfAnswerIsEmpty() {
		if(txt_answer.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private void askQuestion() {
		idx = Helpers.random(this.datasets.size());
		if(!this.datasets.isEmpty()) {
			txt_question.setText(this.datasets.get(idx)[0]);
			txt_answer.setText("");
		} else {
			this.toggleVisibility();
		}
	}

	private void checkAnswer(String answer) {
		if (this.datasets.get(idx)[1].equals(answer)) {
			Messages.showInfo("correct answer");
			this.datasets.remove(idx);
			this.words_right++;
		} else {
			Messages.showInfo("wrong answer");
			this.wrong_datasets.add(datasets.get(idx));
			this.datasets.remove(idx);
			this.words_wrong++;
		}

		/* Learning is finished */
		if (this.datasets.isEmpty()) {
			if (this.wrong_datasets.isEmpty()) {
				saveStatistic();
			} else {
				Object[] options = { "Yes", "No" };
				int res =
					JOptionPane.showOptionDialog(null,
						"You answered " + wrong_datasets.size() + " words wrong. Do you want to learn these again?",
						"Learn again",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null, options, null);

				if (res == 0)
					learnAgain();
				else
					saveStatistic();
			}
		}
	}

	private void learnAgain() {
		for (String[] s : wrong_datasets) {
			this.datasets.add(s);
		}

		this.wrong_datasets.removeAll(wrong_datasets);
		askQuestion();
	}

	private void saveStatistic() {
		percent = (double)words_right / (double)(words_right + words_wrong) * 100;
		Messages.showInfo((int)percent + "% right (" + words_right + " right, " + words_wrong + " wrong)");
		Statistic s = new Statistic(user.getId(), exerciseareaid.getId(), (int)percent, new Date());
		Statistics.addStatistic(connection, s);
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
