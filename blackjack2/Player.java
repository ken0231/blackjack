package cc.shinbi.exercise.blackjack2;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

//プレイヤークラス
public class Player extends Attender {
	//hit(1枚引く)の定数を[1]:(1と入力すればhitとして処理される)
	public static final int ACTION_HIT = 1;
	//スタンド(勝負する)定数を[2]:(2と入力すればスタンドとして処理される)
	public static final int ACTION_STAND = 2;
	//入力された行を読み込む
	private BufferedReader reader;
	
	//コンストラクタ
	//プレイヤー名を入力
	public Player(BufferedReader reader) {
		//Attender(superclass)のコンストラクターを呼び出す
		//参加者の名前にYouを作成
		super("You");
		//入力した行を読み込む
		this.reader = reader;
	}
	@Override
	//手札の強さを計算する
	//サブクラスがスーパークラスの代わりに機能すること
	//superクラスのtoStringメソッドをオーバーライド
	public int calculateStrength() {
		//strength = 参加者(super:コントラスター)の計算された手札
		int strength = super.calculateStrength();
		//勝敗の計算
		//勝ち
		if(strength == 0) {
			//負け
			strength = -1;
		}
		return strength;
	}
	@Override
	//ヒットスタンドの選択
	public void play(Stock stock)throws IOException{
		//standing = false :スタンド(勝負する)
		boolean standing = false;
		//tureの場合(hit :もう1枚引く場合)
		while(!standing) {
			////参加者の状態をディスプレイに表示する
			this.display();
			//action = 選択で1を選ぶ
			int action = selectAction();
			//hitするのに1を入力したら
			if(action == ACTION_HIT) {
				//山札からカードを1枚引く
				this.hit(stock);
				//strength = 手札の合計を計算する
				int strength = this.calculateStrength();
				//手札の合計が0以下なら(barst状態)
				if(strength <= 0) {
					//勝負する(barst) = ture処理
					standing = true;
				}
			}
			else {
				//勝負する(barst)
				standing = true;
			}
		}
	}
	//ディスプレイでの処理
	public int selectAction() throws IOException {
		//1を押したら下記の内容が表示される処理をselectMessageに代入
		//変数actionの作成:初期化
		int action = 0;
		do{
		String selectMessage = "[" + ACTION_HIT + "] Hit(カードを引く)"
				+" [" + ACTION_STAND +"] Stand(勝負する)";
		//変数selectMessage↑の中身を表示する
		System.out.println(selectMessage);
		
		//input =  テキスト行(ACTION_HIT,ACTION_STAND)の読み込み
		String input = this.reader.readLine();
		//複数オブジェクトの内容が同じであるか
		//input(入力された値)の長さが0の場合continue
		//正規表現
		if(input.length() < 1) {
			System.out.println("入力してください");
			continue;
		}
		//(半角数字であるかの検索条件)
		Pattern pattern = Pattern.compile("^[0-9]");
		//数字ならマッチしている
		//マッチしていなかったら:!(continue)
		if(!(pattern.matcher(input).matches())) {
			System.out.println("不適切な文字が入力されました");
			continue;
		}
		//action(0) + (テキスト行の文字列を数字へ変換(1,2))
		//actionと同じ形へ変換
		//エラーの場所
		action = Integer.parseInt(input);
		}
		//action変数が(ACTION_HIT, ACTION_STAND)どちらでもない場合、doへ戻る
		while(action != ACTION_HIT && action != ACTION_STAND);
		//actionへ1or2を返す
		return action;
	}
}
