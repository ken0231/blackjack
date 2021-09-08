package cc.shinbi.exercise.blackjack2;
//カード管理クラス
public class Card {
		//マーク定義
		public enum Suit {
		//スペード
		SPADE,
		//ハート
		HEART,
		//ダイヤ
		DIAMOND,
		//クラブ
		CLUB
		}
		private Suit suit;
		private int number;
		
		//コンストラクター
		public Card(Suit suit, int number) {
			//トランプのマーク
			this.suit = suit;
			//トランプの数字
			this.number = number;
		}
		//トランプのマークを取得するメソッド
		public Suit getSuit() {
			//トランプのマーク
			return suit;
		}
		//トランプの数字を取得するメソッド
		public int getNumber() {
			//トランプの数字
			return number;
		}
		//StringクラスのgetSuitstringメソッド(引数suit)
		//②(変数string(引数suit) = SPADE=♤,HEART=♡,DIAMONDO=♢,CLUB=♧)
		//マーク情報の文字列を取得するメソッド
		public static String getSuitString(Suit suit) {
			//変数stringを作成しnullを代入
			String string = null;
			
			//(Suit.SPADE -&gt;スペード)
			if(suit == Suit.SPADE) {
				//文字列でスペードを取得
				string = "♤";
			}
			else if(suit == Suit.HEART) {
				string = "♡";
			}
			else if(suit == Suit.DIAMOND) {
				string = "♢";
			}
			else if(suit == Suit.CLUB) {
				string = "♧";
			}
			return string;
		}
		//数字情報の文字列
		//stringクラスのgetNumberStringメソッド(引数number)
		//②(変数string(引数number) = A,2,3,4,5,6,7,8,9,10,J,Q,K)
		//文字に変更して表示する必要のある数字の処理〜
		public static String getNumberString(int number) {
			//中身が空の場合文字列にnull
			String string = null;
			
			if(number == 1) {
				string = "A";
			}
			else if(number == 11) {
				string = "J";
			}
			else if(number == 12) {
				string = "Q";
			}
			else if(number ==13) {
				string = "K";
			}
			//2~10の数字は通常どうり数字でstringへ
			else if(number >= 2 && number <= 10) {
				string = Integer.toString(number);
			}
			return string;
		}
		
		//string変数の上記二つを組み合わせる
		public String toString() {
			//①getSuitStringメソッド(引数suit)を呼び出す↓(中身)
			//②(変数string(引数suit) = SPADE=♤,HEART=♡,DIAMONDO=♢,CLUB=♧)
			//③変数suitに代入 :suit = ②getSuitStringメソッド(引数suit)↑(中身)
			String suit = getSuitString(this.suit);
			
			//①getNumberStringメソッド(引数number)を呼び出す↓(中身)
			//②(変数string(引数number) = A,2,3,4,5,6,7,8,9,10,J,Q,K)
			//③変数numberに代入:number = ②getNumberStringメソッド(引数number)↑(中身)
			String number = getNumberString(this.number);
			//string を出力すると[]+中身が出力される
			String string = "[]";
			//もしsuiとnumbertの中身が空(null)でない場合
			if(suit != null && number != null) {
				//上記で作成した変数suit②,number②を
				//toStringメソッドの変数stringの中に代入
				//③string = [suit + number]
				//string =(string型)A,2,3,4,5,6,7,8,9,10,J,Q,K,SPADE=♤,HEART=♡,DIAMONDO=♢,CLUB=♧
				string = "[" + suit + number + "]";
			}
			return string;
		}
		public static Card[] getAllCards() {
			//型名[] 配列変数名 = new 型名[要素数];
			//cardsに配列52個を作成する
			Card[] cards = new Card[52];
			//配列の箱indexを作成して初期化
			int index = 0;
			
			//suitsに4つの配列を作成
			//箱の値は(Suit.SPADE,Suit.HEART,Suit.DIAMOND,Suit.CLUB)
			Suit[] suits = {Suit.SPADE,Suit.HEART,Suit.DIAMOND,Suit.CLUB
			};
			//0~3回繰り返す(suits.length = 4)
			for(int i = 0; i < suits.length; i++) {
				//Suit.SPADE,Suit.HEART,Suit.DIAMOND,Suit.CLUB:出力
				//suitsの中からi選択
				Suit suit = suits[i];
				//1~13を繰り返す(number.length = 13)
				for(int number = 1; number <= 13; number++) {
					//変数cardに(引数suit,number)を代入
					//card = [(string型)A,2,3,4,5,6,7,8,9,10,J,Q,K,
					//SPADE=♤,HEART=♡,DIAMONDO=♢,CLUB=♧]
					Card card = new Card(suit,number);
					//cards=52の配列の中にindex0があるそこに変数cardを代入
					cards[index] = card;
					//マークと文字を0から順番に52まで要素へ入れていく
					//52枚のカードを作成
					index++;
				}
			}
			return cards;
		}
}