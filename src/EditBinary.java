import java.io.*;
import java.nio.ByteBuffer;

public class EditBinary {

    /**
     * main関数
     * @param args
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedOutputStream bos = null;
        int LLSIZE = 2;

        try {
            br = new BufferedReader(new FileReader("input.txt"));
            bos = new BufferedOutputStream(new FileOutputStream("output.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                int recSize = line.length();
                byte ll[] = ByteBuffer.allocate(LLSIZE).putShort((short)recSize).array();
                byte data[] = ByteBuffer.allocate(recSize).put(line.getBytes("UTF-8")).array();
                ByteBuffer lineBuf = ByteBuffer.allocate(LLSIZE + recSize);
                lineBuf.put(ll);
                lineBuf.put(data);
                bos.write(lineBuf.array());
            }
            bos.flush();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
