package classes;

import port.PortReader;

import java.util.ArrayList;
import java.util.Random;

public class GenerateData {

    private boolean stop=false;
    Random generator = new Random();

    private static volatile GenerateData GenerateINSTANCE;

    public boolean stopGenerating(){
        return stop=true;

    }
    public static GenerateData getInstance() {
        if (null == GenerateINSTANCE) {
            synchronized (GenerateData.class) {
                if (null == GenerateINSTANCE) {
                    GenerateINSTANCE = new GenerateData();
                }
            }
        }
        return GenerateINSTANCE;
    }

    private void generateData() throws InterruptedException {
        ArrayList<Integer> data=new ArrayList<>();
        data.clear();

        for(int i=0; i<8;i++){
            data.add(generator.nextInt(100));
        }
        Frame frame=new Frame(data);
        PortReader.getInstance().getFrameBuffer().put(frame);
    }
    public void startGenerating(){
        Thread generateThread=new Thread(()->{

            while(!stop) {
                try {
                    generateData();
                    wait(1000);
                }
                catch (Exception e) {
                    break;
                }
            }
        });
        generateThread.start();

    }
}
