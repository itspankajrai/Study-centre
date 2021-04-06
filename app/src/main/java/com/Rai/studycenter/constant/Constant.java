package com.Rai.studycenter.constant;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Constant {

String website="http://archive.mu.ac.in/myweb_test";
String Fybscit="syllFybscit";
String Communication_Skills="";
    public static final String SharedPref = "SharedPref" ;
    public static final String CollegeKey = "collegeKey" ;
    String firebasePref="firebaseKey";
    String firebaseCollegeKey="uniqueKey";
    String firebaseDefualtUrl="https://www.studycentre.com/";





    String DEQuestions[] = {"The binary number 10101 is equivalent to decimal number …………","The universal gate is ………………","The NOR gate is OR gate followed by ………………","The only function of NOT gate is to ……………","Decimal number 10 is equal to binary number ……………","How many bits are needed to store one BCD digit?","What is the addition of the binary number 101001+ 010011=?","What is the binary subtraction of 101001 - 010110 =?","Which number system has a base 16 ?","Which of the following gates has the exact inverse output of the OR gate for all possible input combinations?"};

    String DEAnswers[]={"21","NAND gate","NOT gate","Invert input signal","1010","4 bits","111100","010011","Hexadecimal","NOR"};

    String DEOptions[]={
            "19","12","27","21",
            "NAND gate","OR gate","AND gate","None of the above",
            "AND gate","NAND gate","NOT gate","None of the above",
            "Stop signal","Invert input signal","Act as a universal gate","None of the above",
            "1110","1010","1001","1000",
            "2 bits","4 bits","3 bits","1 bit",
            "010100","111100","000111","101110",
            "010011","100110","011001","010010",
            "Hexadecimal","Octal","Binary","Decimal",
            "AND","NOT","NOR","NAND"
    };

    String IPquestions[] = {"Who is called as Father of C Programming Language ?","What is Full Name of Dennis Ritchie ?","C Programming was created at __ by Dennis Ritchie","Program is executed by which of the following software ?","Which of the following is appropriate position for writing comment in C Programming ?","Comments in C Programming are ignored by ___.","Conditional Operator is called as _____.","Which keyword is used to come out of a loop only for that iteration?","The keyword break cannot be simple used within:","#include is called"};

    String IPanswers[]={"Dennis Ritchie","Dennis MacAlistair Ritchie","AT&T Bell Laboratory","JVM","anywhere in the code","Compiler","Ternary Operator","break","if-else","Preprocessor directive"};

    String IPoptions[]={
            "Dennis Ritchie","Bill Gates","Ken Thompson","Steve Jobs",
            "Dennis George Ritchie","Dennis MacAlistair Ritchie","Dennis Stephen Ritchie","Dennis Bill Ritchie",
            "L&T Laboratory","MIT University","Haward University","AT&T Bell Laboratory",
            "Compiler","Interpreter","JRE","JVM",
            "before header file section","before calling the main function","before variable declaration section","anywhere in the code",
            "Interpreter","Compiler","Browser"," None of these",
            "Bitwise Operator","Ternary Operator","Binary Operator","Unary Operator",
            "break","continue","return","exit",
            "do-while","if-else","for","while",
            "Preprocessor directive","Inclusion directive","File inclusion directive","File Processing directive"
    };
    String OPquestions[] = {"Which of the following is not an operating system?","What is the maximum length of the filename in DOS?","When was the first operating system developed?","When were MS windows operating systems proposed?","Which of the following is the extension of Notepad?","Which is the Linux operating system?","Which of the following is a single-user operating system?","The size of virtual memory is based on which of the following?","Which of the following is not application software?","Which of the following is a condition that causes deadlock?"};

    String OPanswers[]={"Oracle","8","1950","1985",".txt","Open-source operating system","Ms-Doc","Address Bus","Windows 7","All of these"};

    String OPoptions[]={
            "Windows","Linux","Oracle","DOS",
            "4","5","8","12",
            "1948","1949","1950","1951",
            "1994","1990","1992","1985",
            ".txt",".xls",".ppt",".bmp",
            "Private operating system","Windows operating system","Open-source operating system","None of these",
            "Windows","MAC","Ms-Doc","None of these",
            "CPU","RAM","Address Bus","Data Bus",
            "Windows 7"," WordPad","Photoshop","MS-Excel",
            "Mutual exclusion","Hold and wait","Circular wait","All of these"
    };
    String CSquestions[] = {"The exchange of information, thought and messages through speech, visuals, signals, writing and behaviour","It includes written communication in the form of letters, emails, memoranda, policy manuals,reports,etc","Which of these is not the purpose of Business Correspondence.","Which of these is not 7 c’s of communication","Correctness in the ___ of the letters is mandatory","___ means compact, short in size.","Communication is a process of passing information and understanding from one person to another","Order of the communication process","The final step of communication process is ____.","Which of these is not a non-verbal communication"};

    String CSanswers[]={"Communication","Business Correspondence","Web designing","Classification","Language","Concise","Keith Davis","Sender Message Channel Receiver Feedback","Feedback","oral"};
    String CSoptions[]={
        "Courtesy","Communication","Consideration","Consistency",
                "Business Compilation","Business Competition","Business Correspondence","Business Values",
                "Seeking information","Job applications","Personal letters","Web designing",
                "Classification","Concreteness","Conciseness"," Clarity",
                "Energy level","Tone","Language","Music",
                "Concise","Incisive","Brief","Short",
                "Keith Davis","Robert kreither","The American Association","Peter little",
                "Sender Channel Message Receiver Feedback","Sender Feedback Channel Receiver Message","Feedback Message Channel Receiver Sender","Sender Message Channel Receiver Feedback",
                "Feedback","Encoding","Decoding","Channel selection",
                "Eye contact","oral","Body posture","facial expressions"
    };
}