package cc.shinbi.exercise.blackjack2;

public class Dealer extends Attender{
	//
	private boolean opened;
	
	//コンストラクター
	public Dealer() {
		//参加者の名前にコンピュータを作成
		//Attender(superclass)のコンストラクターを呼び出す
		super("computer");
		/* 
	public Attender(String name) {
		//参加者の名前(仮引数name)
		this.name = name;
		intialize();
	}
		 */
	}
	@Override
	//サブクラスがスーパークラスの代わりに機能すること
	//superクラスのstartをオーバーライド
	public void start(Stock stock) {
		//Attenderのstartメソッドを引数(stock)で呼び出す
		//ゲームを開始する(手札を2枚引く)メソッド(引数stock)
		super.start(stock);
		//この時openedはfalse
		this.opened = false;
	}
	
	@Override
	//サブクラスがスーパークラスの代わりに機能すること
	//superクラスのtoStringメソッドをオーバーライド
	//加えたカード1枚だけ見えているopened:false
	public String toString() {
		//文字列string = " "を取得
		String string = "";
		//opened = falseなら
		if(this.opened) {
			//ディスプレイに参加者の状態を文字列で取得するメソッドを
			//呼び出してstringに代入("" + Attender(string))
			//"player名(上記のstring) + 手札の加えた要素数(カード情報)"
			//2枚オープンしている時の処理(スタート時、手札が1枚見えている状態)
			string = super.toString();
		}
		else {
			//trueの場合:1枚伏せている時の文字列の処理
			//名前 + ": " を取得
			string = this.name + ": ";
			//シャッフルされた状態の山札の要素数,繰り返し読み込む
			for(int i = 0; i < this.cards.size(); i++) {
				//山札から引いた要素数(カード)を手札に加える
				Card card = this.cards.get(i);
				//カードを1枚引いた時
				if(i == 0) {
					//":player名(上記のstring) + 手札の加えた要素数(カード情報)"
					string = string + card.toString();
				}
				else {
					//引かない場合":[#####]"
					string = string + "[#####]";
				}
			}
		}
		//
		return string;
	}
	@Override
	//抽象メソッド:山札からカードを1枚引くhit
	public void play(Stock stock) {
		//手札の強さ = 参加者(super:コントラスター)の計算された手札
		int strength = super.calculateStrength();
		//手札の合計値が17より小さい、合計が1以上であるなら
		while(strength < 17 && strength > 0) {
			//手札にの合計 = さらに追加して計算する
			this.hit(stock);
			strength = this.calculateStrength();
		}//toStringのelse(ture処理を行う)
		this.opened = true;
	}
}
