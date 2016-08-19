
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.herac.tuxguitar.io.gtp.GP4InputStream;
import org.herac.tuxguitar.io.gtp.GTPSettings;
import org.herac.tuxguitar.song.factory.TGFactory;
import org.herac.tuxguitar.song.models.TGBeat;
import org.herac.tuxguitar.song.models.TGMeasure;
import org.herac.tuxguitar.song.models.TGSong;
import org.herac.tuxguitar.song.models.TGTrack;

public class Main {
	public static void main(String[] args){
		TGSong song=null;
		try {
			
			InputStream inputStream = new FileInputStream("test.gp4");
			BufferedInputStream stream= new BufferedInputStream(inputStream);
			GP4InputStream reader = new GP4InputStream(new GTPSettings());
			reader.init(new TGFactory(),stream);
			if(reader.isSupportedVersion()){
				song=reader.readSong();
			}
			
			System.out.println("악보 로드 완료");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(song.getTrack(0).getName());
		Iterator<TGTrack> itt=song.getTracks();
		while(itt.hasNext()){
			TGTrack tgt=itt.next();
			System.out.println(tgt.getName());
		}
		Iterator<TGMeasure> it=song.getTrack(0).getMeasures();
		while(it.hasNext()){
			TGMeasure tgMeasure=(TGMeasure)it.next();
			if(tgMeasure!=null){
				List<TGBeat> list = tgMeasure.getBeats();
				Iterator<TGBeat> it2 =list.iterator();
				while(it2.hasNext()){
					TGBeat tgBeat = (TGBeat)it2.next();
					try{
						//System.out.println(tgBeat.getText().getValue());
					}catch(NullPointerException e){
						
					}
				}
			}
				
				
		}
	}
}
