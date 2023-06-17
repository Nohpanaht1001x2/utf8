package th.co.geniustre.intern.unicode;


import static th.co.geniustre.intern.unicode.Unicode.getChar;

public class UTF8Decoder1 {
    public static  String decode(byte[] utf8s){
        //TODO decode utf8s to String
        StringBuilder sb = new StringBuilder();
        short reset = 0b0000_0000_0000_0000;
        short tempAp = reset;
        short temp;
        short firstMask = 0b0000_0000_0000_1111;
        short secondMask = 0b0000_0000_0011_1111;
        for(int i=0; i<=utf8s.length-3; i+=3){
            tempAp&=reset; //reset itself
            temp = (short) (firstMask&utf8s[i]);
            temp  = (short) (temp << 12); // 8+4
            tempAp |= temp;
            temp = (short) (secondMask&utf8s[i+1]);
            tempAp |= (temp<<6); // shift back 6bit because already have 4 bit before
            temp = (short) (secondMask&utf8s[i+2]);
            tempAp |= (temp); // don't need to shift back
            sb.append(getChar(tempAp));
        }
        return sb.toString();
    }
}
