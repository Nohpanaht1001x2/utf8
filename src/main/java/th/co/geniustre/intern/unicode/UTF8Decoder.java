package th.co.geniustre.intern.unicode;


import static th.co.geniustre.intern.unicode.Unicode.getChar;

public class UTF8Decoder {
    public static  String decode(byte[] utf8s){
        //TODO decode utf8s to String
        StringBuilder sb = new StringBuilder();
        short tempAp = 0b0000_0000_0000_0000;
        short temp = 0b0000_0000_0000_0000;
        for(int i=0; i<=utf8s.length-3; i+=3){
//            System.out.println(Integer.toBinaryString(utf8s[i]).substring(24) + "  "
//                    + Integer.toBinaryString(utf8s[i+1]).substring(24) + " "
//                    + Integer.toBinaryString(utf8s[i+2]).substring(24) + " ");


            tempAp^=tempAp; //reset itself
            temp ^= utf8s[i];temp+=255; //using some mathmagic - reset upper signed bits
//            System.out.println(Integer.toBinaryString(utf8s[i]).substring(24));
            temp  = (short) (temp << 12); // 8+4
            tempAp ^= temp; temp^= temp; //assign bit to tempappend then reset by xor of itself
            temp = (short) utf8s[i+1];
            temp  = (short) (temp << 10); // 8+2
            tempAp ^= (temp>>4); temp^= temp; // shift back 4bit because already have 4 bit before
            temp = (short) utf8s[i+2];
            temp  = (short) (temp << 10); // 8+2
            tempAp ^= (temp>>10); temp^= temp; // shift back 4bit because already have 4 bit before
//            System.out.println(Integer.toBinaryString(tempAp).substring(16));
            sb.append(getChar((int)tempAp));
        }
//        System.out.println(sb.length());
        return sb.toString();
    }
}
