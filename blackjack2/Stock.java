package cc.shinbi.exercise.blackjack2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Stock {
	private ArrayList<Card> cards;
	
	//コンストラター
	public Stock() {
		//initialize()メソッドを呼び出す
		initialize();
	}
	//初期化 (52枚シャッフル)
	public void initialize() {
		//getAllCards()メソッド(52枚のカードを作成)呼び出し
		//配列arrayへ代入
		Card[] array = Card.getAllCards();
		// Arrays.asList()でarrayをサイズ変更可能なlist配列に変換
		//その後、変数listへ代入
		List<Card> list  = Arrays.asList(array);
		//リスト化しているため、Collectionsクラスでlistの中身をシャッフルする
		Collections.shuffle(list);
		//listがシャッフルされた状態、変数cardsを作成
		this.cards = new ArrayList<Card>(list);
	}
	//山札にあと何枚あるか確認するメソッド
	public int getNumberOfCards() {
		//残りの枚数を返す
		//リスト化したのでsize() =「コレクションの要素数」で返す
		return this.cards.size();
	}
	//山札からカードを1枚引くメソッド
	public Card pickCard() {
		//cards(シャッフルされたcard:山札)
		//cardsをcardへ代入
		//cardsから一番上から取り出してcardへ代入
		//Cardクラスにある変数cardの残りの枚数が51枚になる
		Card card = this.cards.get(0);
		//引いた分を削除(0)番目=1番目(1枚)
		//現在のインスタンスの指定した位置から指定した最後の位置までの
		//全文字が削除された新しい文字列を返します。
		this.cards.remove(0);
		
		return card;
	}
}
