package cc.shinbi.exercise.blackjack2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//ブラックジャック(コントロールクラス)
public class BlackJack {
	//メインメソッド
	public  static void main(String[] args)throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		start(reader);
		reader.close();
	}

	//ゲームを開始する
	//ユーザー入力情報読み込みオブジェクト
	public static void start(BufferedReader reader)throws IOException{
		Stock stock = new Stock();
		
		Player player = new Player(reader);
		Dealer dealer = new Dealer();
		
		Attender[] attenders = {player,dealer};
		
		for(int i = 0; i < attenders.length; i++) {
			Attender attender = attenders[i];
			attender.start(stock);
		}
		dealer.display();
		
		for(int i = 0; i < attenders.length; i++) {
			Attender attender = attenders[i];
			attender.play(stock);
		}
		showResult(player,dealer);
	}
	
	//ゲームの結果を表示する
	public static void showResult(Attender player,Attender dealer) {
		dealer.display();
		player.display();
		
		//ディーラーオブジェクト
		int dealerStrength = dealer.calculateStrength();
		//プレイヤーオブジェクト
		int playerStrength = player.calculateStrength();
		
		if(playerStrength > dealerStrength) {
			System.out.println("あなたの勝ちです");
		}
		else if(playerStrength < dealerStrength) {
			System.out.println("あなたの負けです");
		}
		else {
			System.out.println("引き分けです");
		}
	}
}
