package cc.shinbi.exercise.blackjack2;
import java.io.IOException;
import java.util.ArrayList;


//参加者クラス(プレイヤーとディーラーの共通部分)
//抽象クラスではオブジェクトを生成できない
public abstract class Attender {
	//手札
	protected ArrayList<Card>cards;
	//参加者の名前
	protected String name;
	//コンストラクター
	public Attender(String name) {
		//参加者の名前(仮引数name)
		this.name = name;
		intialize();
	}
	
	//cards(シャッフルされた状態の山札)をリスト化して初期化するメソッド
	public void intialize() {
		//シャッフルされた状態の山札を作成(インスタンスの作成)
		this.cards = new ArrayList<Card>();
	}
	
	//ゲームを開始する(手札を2枚引く)メソッド(引数stock)
	public void start(Stock stock) {
		//cardリスト(手札)の全要素をクリア
		this.cards.clear();
		//0~2未満(カードを引くを2回繰り返す)
		for(int i = 0; i < 2; i++) {
			//山札からカードを引くメソッドを呼び出す
			//(StockクラスのpicCardメソッド)
			//
			Card card = stock.pickCard();
			//card(手札)に(引いたカード2枚for(pickCard))追加addする
			this.cards.add(card);
		}
	}
	//参加者の名前を取得する
	public String getName() {
		//参加者
		return name;
	}
	//ヒット(HIT) - 山札から1枚引く
	public void hit(Stock stock) {
		//山札からカードを引くメソッドを呼び出す
		//(StockクラスのpicCardメソッド)を
		Card card = stock.pickCard();
		//手札に1枚加える
		this.cards.add(card);
	}
	
	/**
	 * 手札の強さを計算する。
	 * @return 手札の強さ (0～21)
	 */
	public int calculateStrength() {
		//手札の初期値の合計
		int strength = 0;
		//true = 手札の合計が11以下ならfoundAを11
		//false = 手札の合計が
		boolean foundA = false;
		
		//引いた回数、手札の要素の中にある値の確認を繰り返す
		for(int i = 0; i < this.cards.size(); i++) {
			//シャッフルした山札cardsから引いたカードの値を手札cardに加える
			Card card = this.cards.get(i);
			//手札の数字を取得と10どちらが小さいか計算して
			//pointの小さい方を出す。(2~10まではそのままの値で計算できるから)
			//手札の2~9,はgetNumberで読み込んでそのままpintへ代入
			//10,J,Q,Kは10の値でpointへ代入(Math.min:小さい方を出力)
			//※Aは1にでもなるし10にも変化できるから別処理
			int point = Math.min(card.getNumber(),10);
			//1を引いた時の処理
			if(point == 1){
				//falseからtrueへ変更
				//(1引いた時点でtreuへ変更され処理が行われる)
				foundA = true;
			}
			//手札の合計値(1をひいた時の) ＝ 手札の合計値 + 1 
			strength = strength + point;
		}
		//tureの処理(point == 1)
		//true(1を引いた時)なお手札の合計が11以下なら
		if(foundA && strength <= 11) {
			//手札合計値(11以下)に + 10(上限) = 手札の合計(1をひいた時の)
			strength = strength + 10;
		}
		//手札の合計値が21より大きい場合(barst処理)
		else if(strength > 21) {
			//手札の合計値が0になる(自動でbarst処理)
			strength = 0;
		}
		//手札の合計の値を返す
		return strength;
	}
	//ディスプレイに参加者の状態を文字列で取得するメソッド
	public String toString() {
		//player名 :
		String string = name + ": ";
		//シャッフルされた状態の山札の要素数,繰り返し読み込む
		for(int i = 0; i < this.cards.size(); i++) {
			//山札から引いた要素数(カード)手札に加える
			Card card = this.cards.get(i);
			//string = player名(上記のstring) + 手札の加えた要素数(カード情報)
			string = string + card.toString(); 
		}
		//displayに表示する情報を返す
		return string;
	}
	//参加者の状態をディスプレイに表示する
	public void display() {
		System.out.println(this.toString());
	}
	//プレイする(抽象メソッド) stock = 山札オブジェクト
	//山札から1枚引く(hit)
	public abstract void play(Stock stock)throws IOException; 
}