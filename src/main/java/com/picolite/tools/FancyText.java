package com.picolite.tools;


import org.springframework.context.annotation.Bean;

public class FancyText {



    public static State state = State.STANDARD;

    private static String codeopen = "<blockquote class=\"blockquote\"><p>";

    private static String codeclose = "</p></blockquote>";

    public static String convert(String input)
    {

        input = input.replace("\n", " \n ");
        input = input.replace("\t", " \t ");
        String output = "<p>";
        String[] inputArr = input.split(" ");



        for (int i = 0; i < inputArr.length; i++)
        {
            //update input for parser
            SwitchFunc excecution;

            //enter switch statement
            switch (state)
            {
                case CODE:
                    excecution = new Code();
                    break;
                case LINK:
                    excecution = new Link();
                    break;
                default:
                    excecution = new Standard();
            }
            String tester = excecution.execute(inputArr[i]);
            output += " " + tester;
        }
        System.out.println(output + "</p>");
        return output + "</p>";
    }

    static class Standard implements SwitchFunc
    {

        @Override
        public String execute(String input) {
            switch (input){
                case "*/*":
                    state = State.CODE;
                    return codeopen;
                case "<<":
                    state = State.LINK;
                    return "<a href=\"";
                case "\n":
                    return "</p><p>";
            }
            return input;
        }
    }

    static class Code implements SwitchFunc
    {
        @Override
        public String execute(String input)
        {
            switch (input)
            {
                case "*/*":
                    state = State.STANDARD;
                    return codeclose;
                case "\n":
                    return "</p> <p>";
                case "\t":
                    return " &nbsp&nbsp&nbsp&nbsp ";
            }
            return input;
        }
    }

    static class Link implements SwitchFunc
    {

        @Override
        public String execute(String input) {
            switch (input)
            {
                case ">>":
                    return "\" >";
                case "[[":
                    return "";
                case "]]":
                    state = State.STANDARD;
                    return "</a>";
            }
            return input;
        }
    }

    enum State {
        STANDARD,
        CODE,
        LINK
    }

    interface SwitchFunc
    {
        public String execute(String input);
    }
}
