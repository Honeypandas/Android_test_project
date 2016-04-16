package ywq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by Administrator on 2016/4/12.
 */
public class ywq {


        public static  String readInputStream(InputStream is){

            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            int len =0;
            byte[] buffer=new byte[1024];
            try {
                while((len=is.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                is.close();
                byte[] result=baos.toByteArray();
                return new String(result);
            } catch (IOException e) {
                e.printStackTrace();
                return  null;
            }
        }

}
