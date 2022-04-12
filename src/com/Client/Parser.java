//package com.Client;
//
//import com.Util.Dir;
//import com.Util.WT;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Parser {
//
//    static HashMap<String, WT> vocab = new HashMap<>(); // Will be using the JSON_Handler to read the JSON files
//
//    static void initVocab() {
//        vocab.put("north", WT.NOUN);
//        vocab.put("south", WT.NOUN);
//        vocab.put("east", WT.NOUN);
//        vocab.put("west", WT.NOUN);
//        vocab.put("box", WT.NOUN);
//        vocab.put("button", WT.NOUN);
//        vocab.put("carrot", WT.NOUN);
//        vocab.put("cat", WT.NOUN);
//        vocab.put("chest", WT.NOUN);
//        vocab.put("coin", WT.NOUN);
//        vocab.put("door", WT.NOUN);
//        vocab.put("key", WT.NOUN);
//        vocab.put("finger", WT.NOUN);
//        vocab.put("skeleton key", WT.NOUN);
//        vocab.put("knife", WT.NOUN);
//        vocab.put("lamp", WT.NOUN);
//        vocab.put("leaflet", WT.NOUN);
//        vocab.put("lever", WT.NOUN);
//        vocab.put("paper", WT.NOUN);
//        vocab.put("pencil", WT.NOUN);
//        vocab.put("sack", WT.NOUN);
//        vocab.put("sausage", WT.NOUN);
//        vocab.put("sign", WT.NOUN);
//        vocab.put("slot", WT.NOUN);
//        vocab.put("test", WT.VERB);
//        vocab.put("get", WT.VERB);
//        vocab.put("move", WT.VERB);
//        vocab.put("i", WT.VERB); // abbrev. for inventory
//        vocab.put("inventory", WT.VERB);
//        vocab.put("take", WT.VERB);
//        vocab.put("drop", WT.VERB);
//        vocab.put("put", WT.VERB);
//        vocab.put("l", WT.VERB); // abbrev. for look
//        vocab.put("look", WT.VERB);
//        vocab.put("open", WT.VERB);
//        vocab.put("close", WT.VERB);
//        vocab.put("pull", WT.VERB);
//        vocab.put("push", WT.VERB);
//        vocab.put("north", WT.VERB);
//        vocab.put("south", WT.VERB);
//        vocab.put("west", WT.VERB);
//        vocab.put("east", WT.VERB);
//        vocab.put("up", WT.VERB);
//        vocab.put("down", WT.VERB);
//        vocab.put("q", WT.VERB);
//        vocab.put("quit", WT.VERB);
//        vocab.put("a", WT.ARTICLE);
//        vocab.put("an", WT.ARTICLE);
//        vocab.put("the", WT.ARTICLE);
//        vocab.put("in", WT.PREPOSITION);
//        vocab.put("into", WT.PREPOSITION);
//        vocab.put("at", WT.PREPOSITION);
//    }
//
//    static String processVerbNounPrepositionNoun(List<WordAndType> command) { // allowing user to use at, to, the, on and the parser won't throw an exception
//        WordAndType wt = command.get(0);
//        WordAndType wt2 = command.get(1);
//        WordAndType wt3 = command.get(2);
//        WordAndType wt4 = command.get(3);
//        String msg = "";
//
//        if ((wt.getWordtype() != WT.VERB) || (wt3.getWordtype() != WT.PREPOSITION)) {
//            msg = "Can't do this because I don't understand how to '" + wt.getWord() + " something " + wt3.getWord() + "' something!";
//        } else if (wt2.getWordtype() != WT.NOUN) {
//            msg = "Can't do this because '" + wt2.getWord() + "' is not thing!\r\n";
//        } else if (wt4.getWordtype() != WT.NOUN) {
//            msg = "Can't do this because '" + wt4.getWord() + "' is not a thing!\r\n";
//        } else {
//            switch (wt.getWord() + wt3.getWord()) {
//                case "putin":
//                case "putinto":
//                    msg = Main.game.putObInContainer(wt2.getWord(), wt4.getWord());
//                    break;
//                default:
//                    msg = "I don't know how to " + wt.getWord() + " " + wt2.getWord() + " " + wt3.getWord() + " " + wt4.getWord() + "!";
//                    break;
//            }
//        }
//        return msg;
//    }
//
//    static String processVerbPrepositionNoun(List<WordAndType> command) {
//        // "look at" is the only implemented command of this type
//        WordAndType wt = command.get(0);
//        WordAndType wt2 = command.get(1);
//        WordAndType wt3 = command.get(2);
//        String msg = "";
//
//        if ((wt.getWordtype() != WT.VERB) || (wt2.getWordtype() != WT.PREPOSITION)) {
//            msg = "Can't do this because I don't understand '" + wt.getWord() + " " + wt2.getWord() + "' !";
//        } else if (wt3.getWordtype() != WT.NOUN) {
//            msg = "Can't do this because '" + wt3.getWord() + "' is not an object!\r\n";
//        } else {
//            switch (wt.getWord() + wt2.getWord()) {
//                case "lookat":
//                    msg = Main.game.lookAtOb(wt3.getWord());
//                    break;
//                case "lookin":
//                    msg = Main.game.lookInOb(wt3.getWord());
//                    break;
//                default:
//                    msg = "I don't know how to " + wt.getWord() + " " + wt2.getWord() + " " + wt3.getWord() + "!";
//                    break;
//            }
//        }
//        return msg;
//    }
//
//    static String processVerbNoun(List<WordAndType> command) {
//        WordAndType wt = command.get(0);
//        WordAndType wt2 = command.get(1);
//        String msg = "";
//
//        if (wt.getWordtype() != WT.VERB) {
//            msg = "Can't do this because '" + wt.getWord() + "' is not a command!";
//        } else if (wt2.getWordtype() != WT.NOUN) {
//            msg = "Can't do this because '" + wt2.getWord() + "' is not a thing!";
//        } else {
//            switch (wt.getWord()) {
//                case "take":
//                case "get":
//                    msg = Main.game.takeOb(wt2.getWord());
//                    break;
//                case "drop":
//                    msg = Main.game.dropOb(wt2.getWord());
//                    break;
//                case "open":
//                    msg = Main.game.openOb(wt2.getWord());
//                    break;
//                case "close":
//                    msg = Main.game.closeOb(wt2.getWord());
//                    break;
//                case "move":
//                default:
//                    msg += " (Not done yet)";
//                    break;
//            }
//        }
//        return msg;
//    }
//
//    static String processVerb(List<WordAndType> command) {
//        WordAndType wt = command.get(0);
//        String msg = "";
//
//        if (wt.getWordtype() != WT.VERB) {
//            msg = "Can't do this because '" + wt.getWord() + "' is not a command!";
//        } else {
//            switch (wt.getWord()) {
//                case "north":
//                case "move north":
//                    Main.game.goN();
//                    break;
//                case "south":
//                case "move south":
//                    Main.game.goS();
//                    break;
//                case "west":
//                case "move west":
//                    Main.game.goW();
//                    break;
//                case "east":
//                case "move east":
//                    Main.game.goE();
//                    break;
//                case "up":
//                    Main.game.goUp();
//                    break;
//                case "down":
//                    Main.game.goDown();
//                    break;
//                case "l":
//                case "look":
//                    Main.game.look();
//                    break;
//                case "inventory":
//                case "i":
//                    Main.game.showInventory();
//                    break;
//                default:
//                    msg = wt.getWord() + " (not yet implemented)";
//                    break;
//            }
//        }
//        return msg;
//    }
//
//    static String processCommand(List<WordAndType> command) {
//        String s = "";
//
//        if (command.size() == 0) {
//            s = "You must write a command!";
//        } else if (command.size() > 4) {
//            s = "That command is too long!";
//        } else {
//            switch (command.size()) {
//                case 1:
//                    s = processVerb(command);
//                    break;
//                case 2:
//                    s = processVerbNoun(command);
//                    break;
//                case 3:
//                    s = processVerbPrepositionNoun(command);
//                    break;
//                case 4:
//                    s = processVerbNounPrepositionNoun(command);
//                    break;
//                default:
//                    s = "Unable to understand";
//                    break;
//            }
//        }
//        return s;
//    }
//
//    static String parseCommand(List<String> wordlist) {
//        List<WordAndType> command = new ArrayList<>();
//        WT wordtype;
//        String errmsg = "";
//        String msg;
//
//        for (String k : wordlist) {
//            if (vocab.containsKey(k)) {
//                wordtype = vocab.get(k);
//                if (wordtype == WT.ARTICLE) {       // ignore articles
//                } else {
//                    command.add(new WordAndType(k, wordtype));
//                }
//            } else { // if word not found in vocab
//                command.add(new WordAndType(k, WT.ERROR));
//                errmsg = "Sorry, I don't understand '" + k + "'";
//            }
//        }
//        if (!errmsg.isEmpty()) {
//            msg = errmsg;
//        } else {
//            msg = processCommand(command);
//        }
//        return msg;
//    }
//
//    static List<String> wordList(String input) {
//        String delims = "[ \t,.:;?!\"']+";
//        List<String> strlist = new ArrayList<>();
//        String[] words = input.split(delims);
//
//        for (String word : words) {
//            strlist.add(word);
//        }
//        return strlist;
//    }
//}
