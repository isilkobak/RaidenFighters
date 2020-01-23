import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

import java.util.Random;
import java.util.Scanner;

public class Game extends JFrame implements KeyListener {

	Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
	Font font2 = new Font(Font.MONOSPACED, Font.BOLD, 50);
	
	Boolean restartFlag = false;
	
	JPanel theEnd;
	
	JLabel kullaniciHata;

	JFrame frame;
	JPanel panel;
	JLabel label, labelBackGround;
	JLabel enemy1, enemy2, enemy3;
	JLabel tank1, tank2, tank3;
	JLabel bullet, bomb;
	JLabel enemyBullet, enemyBullet2, enemyBullet3;
	JLabel score;
	JLabel myExplosion;
	JFrame homePage;

	String userName = "";
	String lastName = "";

	String currentHighScore = "";
	String currentHighScoreName="";
	String currentHighScoreSurname="";

	int myLife = 5;

	int dummyX = 0, dummyY = 0, bombX = 0, bombY = 0;

	/*
	 * ArrayList bulletList = new ArrayList<>(); ArrayList scoreList = new
	 * ArrayList <>() ;
	 */

	int myScore = 0;

	Random random = new Random();
	int myPlaneX = 240;
	int myPlaneY = 400;

	int enemy1x = random.nextInt(400);
	int enemy1y = -20;

	int enemy2x = random.nextInt(400);
	int enemy2y = -20;

	int enemy3x = random.nextInt(400);
	int enemy3y = -20;

	int tank1x = 240;
	int tank1y = -20;

	int tank2x = 130;
	int tank2y = -20;

	int tank3x = 410;
	int tank3y = -20;

	int[] tankArray = { 130, 185, 240, 300, 355, 410 };

	myGame mygame;

	Icon myBullet = new ImageIcon(getClass().getResource("bullet.png"));
	Icon myPlane = new ImageIcon(getClass().getResource("BKPlane.png"));
	Icon enemyPhoto = new ImageIcon(getClass().getResource("EnemyPlane.png"));
	Icon enemyBul = new ImageIcon(getClass().getResource("bullet2.png"));
	Icon explosion = new ImageIcon(getClass().getResource("explosion.gif"));
	Icon tank = new ImageIcon(getClass().getResource("Tank.png"));
	Icon gameOver = new ImageIcon(getClass().getResource("gameover.jpg"));
	Icon tankBomb = new ImageIcon(getClass().getResource("TankMermisi.png"));

	JLabel gameover = new JLabel(gameOver);

	public void createGUI() throws IOException {

		String myString = "";
		score = new JLabel();
		score.setBounds(10, -10, 200, 50);
		score.setFont(font);
		score.setForeground(Color.white);
		score.setText("SCORE:" + myString);
		
		
		kullaniciHata = new JLabel();
		kullaniciHata.setBounds(100,250,200,50);
		
		kullaniciHata.setForeground(Color.red);
		kullaniciHata.setBackground(Color.red);
		kullaniciHata.setText("Hatali Kullanici Bilgileri");
		
		kullaniciHata.setVisible(false);

		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 600);

		Icon background = new ImageIcon(getClass().getResource("background.png"));

		labelBackGround = new JLabel(background);
		labelBackGround.setBounds(0, 0, 600, 600);

		label = new JLabel(myPlane);
		label.setBounds(myPlaneX, myPlaneY, 80, 80);

		enemy1 = new JLabel(enemyPhoto);
		enemy1.setBounds(this.enemy1x, this.enemy1y, 50, 50);

		enemy2 = new JLabel(enemyPhoto);
		enemy2.setBounds(this.enemy2x, this.enemy2y, 50, 50);

		enemy3 = new JLabel(enemyPhoto);
		enemy3.setBounds(this.enemy3x, this.enemy3y, 50, 50);

		tank1 = new JLabel(tank);
		tank1.setBounds(this.tank1x, this.tank1y, 50, 50);

		tank2 = new JLabel(tank);
		tank2.setBounds(this.tank2x, this.tank2y, 50, 50);

		tank3 = new JLabel(tank);
		tank3.setBounds(this.tank3x, this.tank3y, 50, 50);

		myExplosion = new JLabel(explosion);
		panel.add(label);
		panel.add(labelBackGround);

		frame.add(score);

		frame.add(enemy1);
		frame.add(enemy2);
		frame.add(enemy3);

		frame.add(tank1);
		frame.add(tank2);
		frame.add(tank3);

		frame.add(panel);

		frame.setSize(600, 600);
		frame.addKeyListener(this);

		homePage = new JFrame("Basakurt's Game");
		homePage.setBounds(0, 0, 400, 400);

		final JTextField name = new JTextField("Enter the name");

		final JTextField surname = new JTextField("Enter the surname");

		JPanel homePanel = new JPanel();
		homePanel.setBackground(Color.BLACK);

		homePage.setSize(400, 400);

		homePage.add(name);
		homePage.add(surname);
		homePage.add(kullaniciHata);
		
		surname.setBounds(100, 200, 200, 20);
		name.setBounds(100, 150, 200, 20);
		JToggleButton myButton;
		myButton = new JToggleButton("PLAY");

		homePanel.add(myButton);

		homePage.add(homePanel);
		homePage.setVisible(true);

		myButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JToggleButton myButton = (JToggleButton) e.getSource();
				if (myButton.isSelected()) {
					

					if(restartFlag){
						restartFlag = false;
						
						
						
					}
					
					System.out.println(name.getText());

					
					boolean giris = false;
					
					userName = name.getText().toString();
					lastName = surname.getText().toString();
					
					
					File fileKullaniciBilgileri = new File("kullanicibilgileri.txt");
					
					if(!fileKullaniciBilgileri.exists()){
						System.out.println("Kullanici Bilgileri File Eksik.");
					}else{
						try {
							Scanner sc = new Scanner(fileKullaniciBilgileri);
							
							while(sc.hasNext()){
								String[] kullaniciBilgileri = sc.nextLine().split("-");
								
								if(kullaniciBilgileri[0].equals(userName) && kullaniciBilgileri[1].equals(lastName)){
									giris = true;
									break;
								}
											
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
												
					}
					
					
					if(giris){
						File f = new File("highscore.txt");
						if (f.exists()) {
							try {
								
								// Program baslayinca High Score olanin bilgilerini alma
								Scanner sc = new Scanner(f);
								String[] dummyList = sc.nextLine().split("-");
								currentHighScoreName =dummyList[0];
								currentHighScoreSurname = dummyList[1];
								currentHighScore = dummyList[2];
								
								sc.close();
								
								System.out.println(currentHighScore);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						mygame = new myGame();
						mygame.start();
						homePage.setVisible(false);
						frame.setVisible(true);
						kullaniciHata.setVisible(false);
					}else{
						//Buraya Hatali Giris Bilgisi Labeli koy kirmiziyla normalde programin basinda visible false yap
						// burada true yap
						kullaniciHata.setVisible(true);
						System.out.println("Kullanici Bilgileri Yanlis");
						
					}

					
				}
			}
		});

	}

	public int getMyScore() {
		return myScore;
	}

	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}

	public Game() {

	}

	public boolean intersects(JLabel testa, JLabel testb) {
		Rectangle rectB = testb.getBounds();

		Rectangle result = SwingUtilities.computeIntersection(testa.getX(), testa.getY(), testa.getWidth(),
				testa.getHeight(), rectB);

		return (result.getWidth() > 0 && result.getHeight() > 0);
	}

	@Override
	public void keyPressed(KeyEvent e) {

		String whichKey = KeyEvent.getKeyText(e.getKeyCode());

		if (whichKey.compareTo("Left") == 0) {
			changeLayoutLeft();
		}
		if (whichKey.compareTo("Right") == 0) {
			changeLayoutRight();
		}
		if (whichKey.compareTo("Up") == 0) {
			changeLayoutUp();
		}
		if (whichKey.compareTo("Down") == 0) {
			changeLayoutDown();
		}
		if (whichKey.compareTo("Space") == 0) {
			// Mermi
			bullet = new JLabel(myBullet);
			bullet.setBounds(700, 7000, 30, 30);

			JLabel myExplosion = new JLabel(explosion);
			myExplosion.setBounds(700, 700, 100, 100);

			frame.add(myExplosion);
			frame.add(bullet);
			frame.add(panel);

			frame.revalidate();
			frame.repaint();
			frame.validate();

			Fire fire = new Fire(bullet, myExplosion);
			fire.start();

		}

		if (whichKey.compareTo("Enter") == 0) {

			bomb = new JLabel(tankBomb);
			bomb.setBounds(700, 700, 30, 30);
			frame.add(myExplosion);
			frame.add(bomb);
			frame.add(panel);

			frame.revalidate();
			frame.repaint();
			frame.validate();

			Bomb bomb1 = new Bomb(bomb, myExplosion);
			bomb1.start();

		}

	}

	public void movePlane(int newX, int newY) {
		label.setBounds(newX, newY, 80, 80);
	}

	private void changeLayoutDown() {
		if (myPlaneY < 480) {
			myPlaneY = myPlaneY + 20;
			movePlane(myPlaneX, myPlaneY);
		}
	}

	private void changeLayoutUp() {
		if (myPlaneY > 0) {
			myPlaneY = myPlaneY - 20;
			movePlane(myPlaneX, myPlaneY);
		}
	}

	private void changeLayoutRight() {
		if (myPlaneX < 500) {
			myPlaneX = myPlaneX + 20;
			movePlane(myPlaneX, myPlaneY);
		}
	}

	private void changeLayoutLeft() {
		if (myPlaneX > 0) {
			myPlaneX = myPlaneX - 20;
			movePlane(myPlaneX, myPlaneY);
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	class myGame extends Thread {

		@Override
		public void run() {
			try {

				while (true) {
					if (myLife > 0) {

						int x = random.nextInt(100);
						int y = random.nextInt(6);
						enemy1y = enemy1y + 11;
						enemy2y = enemy2y + 13;
						enemy3y = enemy3y + 15;

						tank1y = tank1y + 5;
						tank2y = tank2y + 6;
						tank3y = tank3y + 7;

						if ((enemy1y >= 500)) {
							enemy1y = -50;
							enemy1x = random.nextInt(450);

						}
						if ((enemy2y >= 500)) {
							enemy2y = -50;
							enemy2x = random.nextInt(450);
						}

						if ((enemy3y >= 500)) {
							enemy3y = -50;
							enemy3x = random.nextInt(450);
						}

						if (tank1y >= 500) {
							tank1y = -50;
							tank1x = tankArray[y];

						}
						if (tank2y >= 500) {
							tank2y = -50;
							tank2x = tankArray[y];

						}

						if (tank3y >= 500) {
							tank3y = -50;
							tank3x = tankArray[y];

						}
						if ((x % 12 == 0)) {
							enemyBullet = new JLabel(enemyBul);
							enemyBullet.setBounds(700, 7000, 24, 20);
							frame.add(enemyBullet);
							frame.add(panel);
							frame.revalidate();
							frame.repaint();
							frame.validate();

							EnemyFire fire2 = new EnemyFire(enemyBullet, enemy1x, enemy1y);
							fire2.start();

						}
						if (x % 23 == 0) {
							enemyBullet2 = new JLabel(enemyBul);

							enemyBullet2.setBounds(700, 7000, 24, 20);
							frame.add(enemyBullet2);
							frame.add(panel);
							frame.revalidate();
							frame.repaint();
							frame.validate();
							EnemyFire fire3 = new EnemyFire(enemyBullet2, enemy2x, enemy2y);
							fire3.start();

						}
						if (x % 17 == 0) {
							enemyBullet3 = new JLabel(enemyBul);
							enemyBullet3.setBounds(700, 7000, 24, 20);
							frame.add(enemyBullet3);
							frame.add(panel);
							frame.revalidate();
							frame.repaint();
							frame.validate();
							EnemyFire fire4 = new EnemyFire(enemyBullet3, enemy3x, enemy3y);
							fire4.start();
						}

						enemy1.setBounds(enemy1x, enemy1y, 50, 50);
						enemy2.setBounds(enemy2x, enemy2y, 50, 50);
						enemy3.setBounds(enemy3x, enemy3y, 50, 50);
						tank1.setBounds(tank1x, tank1y, 50, 50);
						tank2.setBounds(tank2x, tank2y, 50, 50);
						tank3.setBounds(tank3x, tank3y, 50, 50);
						Thread.sleep(200);

					} else {

						panel.setVisible(false);

						theEnd = new JPanel();
						theEnd.setBackground(Color.black);
						theEnd.setBounds(0, 0, 600, 600);
						theEnd.setVisible(true);
						theEnd.add(gameover);
						gameover.setBounds(100, 0, 355, 200);
						score.setBounds(150, 0, 600, 50);
						score.setText("SCORE : " + myScore);
						score.setBounds(150, 0, 600, 50);
						score.setText("SCORE : " + myScore);
						score.setFont(font2);
						theEnd.add(score);
						score.setFont(font2);
						frame.add(theEnd);
						frame.setVisible(true);
						frame.revalidate();
						frame.repaint();
						frame.validate();
						enemy1.setVisible(false);
						enemy2.setVisible(false);
						enemy3.setVisible(false);
						tank1.setVisible(false);
						tank2.setVisible(false);
						tank3.setVisible(false);
						
						

						// ScoreText
						File f = new File("highscore.txt");

						if (!f.exists()) {
							PrintWriter pw = new PrintWriter(f);
							pw.write(userName + "-" + lastName + "-" + myScore);
							pw.close();

						} else {
							Scanner sc = new Scanner(f);
							String[] list = sc.nextLine().split("-");
							if (Integer.parseInt(list[2]) < myScore) {
								System.out.println(list[2]);
								f.delete();
								PrintWriter pw = new PrintWriter(f);
								pw.write(userName + "-" + lastName + "-" + myScore);
								pw.close();
								sc.close();
							}
						}
						
						homePage.setVisible(true);
						
						restartFlag = true;
						//frame.setVisible(true);

						this.stop();

					}
					/*
					 * if(intersects(enemy1, label)|intersects(enemy2,
					 * label)||intersects(enemy3,label)){
					 * myExplosion.setBounds(myPlaneX, myPlaneY, 30, 30);
					 * this.stop();
					 * 
					 * 
					 * }
					 */
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	class Fire extends Thread {

		JLabel bullet;
		JLabel myExplosion;

		public Fire(JLabel bullet, JLabel myExplosion) {
			super();
			this.bullet = bullet;
			this.myExplosion = myExplosion;

		}

		@Override
		public void run() {

			bullet.setBounds(myPlaneX, myPlaneY - 20, 25, 25);
			boolean loop = true;

			boolean hit = false;

			boolean tekVurus = true;

			dummyX = myPlaneX + 33;
			dummyY = myPlaneY;
			try {
				while (true) {

					dummyY = dummyY - 10;
					if (dummyY <= -150) {

						myExplosion.setBounds(7000, 7000, 30, 30);
						// loop = false;
						stop();
						destroy();

					}

					if (!hit) {
						bullet.setBounds(dummyX, dummyY, 25, 25);
					}

					if (tekVurus) {

						if (((dummyX >= enemy1x - 20) && (dummyX < enemy1x + 40) && (dummyY < enemy1y + 40)
								&& (myPlaneY > enemy1y))) {
							hit = true;

							bullet.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(enemy1x - 25, enemy1y, 100, 100);

							enemy1y = 7000;
							enemy1x = 7000;

							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						} else if (((dummyX >= enemy2x - 20) && (dummyX < enemy2x + 40) && (dummyY < enemy2y + 40)
								&& (myPlaneY > enemy2y))) {
							hit = true;
							bullet.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(enemy2x - 25, enemy2y, 100, 100);

							enemy2y = 7000;
							enemy2x = 7000;

							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						} else if (((dummyX >= enemy3x - 20) && (dummyX < enemy3x + 40) && (dummyY < enemy3y + 40)
								&& (myPlaneY > enemy3y))) {
							hit = true;
							bullet.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(enemy3x - 25, enemy3y, 100, 100);

							enemy3y = 7000;
							enemy3x = 7000;

							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						}
					}

					sleep(20);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	class EnemyFire extends Thread {

		JLabel enemyBullet;
		int xCoor, yCoor;

		EnemyFire(JLabel enemyBullet, int xCoor, int yCoor) {
			super();
			this.enemyBullet = enemyBullet;
			this.xCoor = xCoor;
			this.yCoor = yCoor;

		}

		public void run() {

			xCoor = xCoor + 13;
			yCoor = yCoor + 10;
			enemyBullet.setBounds(xCoor, yCoor, 25, 25);

			try {

				while (true) {

					if (yCoor >= 1000) {
						stop();
						destroy();
					}

					if (intersects(enemyBullet, label)) {
						myLife--;
						System.out.println("hello");
						yCoor = 1000;
					}

					enemyBullet.setBounds(xCoor, yCoor, 25, 25);
					yCoor = yCoor + 8;

					sleep(30);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	class Bomb extends Thread {

		JLabel bomb;
		JLabel myExplosion;

		public Bomb(JLabel bomb, JLabel myExplosion) {
			super();
			this.bomb = bomb;
			this.myExplosion = myExplosion;

		}

		public void run() {

			bomb.setBounds(myPlaneX, myPlaneY - 20, 25, 25);

			boolean loop = true;

			boolean hit = false;

			boolean tekVurus = true;

			bombX = myPlaneX + 33;
			bombY = myPlaneY;
			try {
				while (true) {

					bombY = bombY - 10;
					if (bombY <= -150) {

						myExplosion.setBounds(7000, 7000, 30, 30);
						// loop = false;
						stop();
						destroy();

					}

					if (!hit) {
						bomb.setBounds(bombX, bombY, 25, 25);
					}

					if (tekVurus) {

						if (((bombX >= tank1x - 20) && (bombX < tank1x + 40) && (bombY < tank1y + 50)
								&& (myPlaneY > tank1y))) {
							hit = true;

							bomb.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(tank1x - 25, tank1y, 100, 100);

							tank1y = 7000;
							tank1x = 7000;
							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						} else if (((bombX >= tank2x - 20) && (bombX < tank2x + 40) && (bombY < tank2y + 50)
								&& (myPlaneY > tank2y))) {
							hit = true;
							bomb.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(tank2x - 25, tank2y, 100, 100);

							tank2y = 7000;
							tank2x = 7000;

							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						} else if (((bombX >= tank3x - 20) && (bombX < tank3x + 40) && (bombY < tank3y + 50)
								&& (bombY > tank3y))) {
							hit = true;
							bomb.setBounds(7000, 7000, 25, 25);

							myExplosion.setBounds(tank3x - 25, tank3y, 100, 100);

							tank3x = 7000;
							tank3y = 7000;

							if (tekVurus) {
								myScore += 5;
								String myString = Integer.toString(myScore);
								score.setText("Score:" + myString);
								tekVurus = false;
							}

						}
					}

					sleep(20);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
