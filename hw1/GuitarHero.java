import java.util.Objects;

/**
 * Created by huangqiming on 2017/1/21.
 */
public class GuitarHero {

    public static void main(String[] args){
        int KEYS = 37;
        double[] CONCERTS = new double[KEYS];
        for(int i = 0; i < KEYS; i++){
            CONCERTS[i] = 440 * Math.pow(2, ((i - 24) / 12));
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        synthesizer.GuitarString[] guitarStrings = new synthesizer.GuitarString[KEYS];
        for (int i = 0; i < KEYS; i++){
            guitarStrings[i] = new synthesizer.GuitarString(CONCERTS[i]);
        }

        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index < 0){
                    continue;
                }
                guitarStrings[index].pluck();
            }
            double sample = 0;
            for (int i = 0; i < KEYS; i++){
                sample += guitarStrings[i].sample();
            }

            StdAudio.play(sample);

            for(int i = 0; i < KEYS; i++){
                guitarStrings[i].tic();
            }
        }
    }
}
