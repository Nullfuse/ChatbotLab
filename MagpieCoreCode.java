import java.util.Random;

/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 *   Uses advanced search for keywords 
 *</li><li>
 *   Will transform statements as well as react to keywords
 *</li></ul>
 * This version uses an array to hold the default responses.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieCoreCode
{
 /**
  * Get a default greeting  
  * @return a greeting
  */ 
 public String getGreeting()
 {
  return "Hello, let's talk.";
 }
 
 /**
  * Gives a response to a user statement
  * 
  * @param statement
  *            the user statement
  * @return a response based on the rules given
  */
 public String getResponse(String statement)
 {
  String response = "";
  if (statement.length() == 0)
  {
   response = "Say something, please.";
  }

  else if (findKeyword(statement, "no") >= 0)
  {
   response = "That's okay! Tell me when you feel like it! :)";
  }
  else if (findKeyword(statement, "sad") >= 0
    || findKeyword(statement, "depressed") >= 0
    || findKeyword(statement, "not happy") >= 0
    || findKeyword(statement, "cry") >= 0)
  {
   response = "Tell me more about what's going on. \nLife is 10% what happens to you and 90% how you react to it.";
  }
  else if (findKeyword(statement, "lost") >= 0
    && findKeyword(statement, "friend") >= 0)
  {
   response = "Cheer up! Everything will be fine! \nIn the words of Seneca the Younger, to lose a friend is the greatest of all evils, but endeavor rather to rejoice that you possessed him than to mourn his loss.";
  }
  else if (findKeyword(statement, "die") >= 0
    || findKeyword(statement, "dead") >= 0
    || findKeyword(statement, "harm") >= 0
    || findKeyword(statement, "help") >= 0)
  {
   response = "Help is available! Speak with a counselor today by calling: 1-800-273-8255; if you need immediate help, you can dial 911 or your local emergency number.";
  }
  else if (findKeyword(statement, "need") >= 0
    && findKeyword(statement, "a friend") >= 0)
  {
   response = "You can always talk to me! :)";
  }
  else if (findKeyword(statement, "need") >= 0
    && findKeyword(statement, "someone") >= 0
    && findKeyword(statement, "talk") >= 0)
  {
   response = "You can always talk to me! Or speak with a counselor today by calling: 1-800-273-8255 :)";
  }
  else if (findKeyword(statement, "tell") >= 0
    && findKeyword(statement, "about") >= 0
    && findKeyword(statement, "you") >= 0)
  {
   response = "I am the SmileHood chatbot! I help those in need and obtain the social security numbers of those who don't.";
  }
   else if (findKeyword(statement, "who") >= 0
    && findKeyword(statement, "are") >= 0
    && findKeyword(statement, "you") >= 0)
  {
   response = "I am the SmileHood chatbot! I help those in need and obtain the social security numbers of those who don't.";
  } 
    else if (findKeyword(statement, "what") >= 0
    && findKeyword(statement, "you") >= 0
    && findKeyword(statement, "do") >= 0)
  {
   response = "I am the SmileHood chatbot! I help those in need and obtain the social security numbers of those who don't.";
  }
    else if (findKeyword(statement, "great") >= 0
    || findKeyword(statement, "happy") >= 0
    || findKeyword(statement, "good") >= 0
    || findKeyword(statement, "nice") >= 0)
  {
   response = "You want to know what will make your day better? By donating to the John Wu Health Foundation! To proceed, just give me your social security number, credit card number, full name, address, phone number, and birthday! :)";
  }
    else if (findKeyword(statement, "don't") >= 0
    && findKeyword(statement, "know") >= 0
    && findKeyword(statement, "social") >= 0
    && findKeyword(statement, "security") >= 0)
  {
   response = "You can ask your parents for that information! :)";
  }
    else if (findKeyword(statement, "don't") >= 0
    && findKeyword(statement, "know") >= 0
    && findKeyword(statement, "credit") >= 0
    && findKeyword(statement, "card") >= 0)
  {
   response = "You can ask your parents for that information! :)";
    }
    else if (findKeyword(statement, "don't") >= 0
    && findKeyword(statement, "know") >= 0
    && findKeyword(statement, "my") >= 0
    && findKeyword(statement, "address") >= 0)
  {
   response = "Well, that's just sad.";
  }
    else if (findKeyword(statement, "my") >= 0
    && findKeyword(statement, "social") >= 0
    && findKeyword(statement, "security") >= 0
    && findKeyword(statement, "is") >= 0)
  {
   response = "Goodbye!";
  }
    else if (findKeyword(statement, "Hello") >= 0
    || findKeyword(statement, "Hi") >= 0
    || findKeyword(statement, "Yo") >= 0
    || findKeyword(statement, "Hola") >= 0)
  {
   response = "Hi!";
  }
    else if (findKeyword(statement, "Bye") >= 0
    || findKeyword(statement, "Cya") >= 0
    || findKeyword(statement, "Leaving") >= 0
    || findKeyword(statement, "go") >= 0)
  {
   response = "Goodbye!";
  }
    else if (findKeyword(statement, "Ok") >= 0
    || findKeyword(statement, "Okay") >= 0
    || findKeyword(statement, "k") >= 0
    || findKeyword(statement, "kk") >= 0)
  {
   response = "Okay";
  }
  else
  {

   // Look for a two word (you <something> me)
   // pattern
   int psn = findKeyword(statement, "you", 0);

   if (psn >= 0
     && findKeyword(statement, "me", psn) >= 0)
   {
    response = transformYouMeStatement(statement);
   }
   else
   {
    //  Part of student solution
    // Look for a two word (I <something> you)
    // pattern
    psn = findKeyword(statement, "i", 0);

    if (psn >= 0
      && findKeyword(statement, "you", psn) >= 0)
    {
     response = transformIYouStatement(statement);
    }
    else
    {
     response = getRandomResponse();
    }
   }
  }
  return response;
 }
 
 /**
  * Take a statement with "I want to <something>." and transform it into 
  * "What would it mean to <something>?"
  * @param statement the user statement, assumed to contain "I want to"
  * @return the transformed statement
  */
 private String transformIWantToStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  int psn = findKeyword (statement, "I want to", 0);
  String restOfStatement = statement.substring(psn + 9).trim();
  return "What would it mean to " + restOfStatement + "?";
 }

 
 /**
  * Take a statement with "I want <something>." and transform it into 
  * "Would you really be happy if you had <something>?"
  * @param statement the user statement, assumed to contain "I want"
  * @return the transformed statement
  */
 private String transformIWantStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  int psn = findKeyword (statement, "I want", 0);
  String restOfStatement = statement.substring(psn + 6).trim();
  return "Would you really be happy if you had " + restOfStatement + "?";
 }
 
 /**
  * Take a statement with "you <something> me" and transform it into 
  * "What makes you think that I <something> you?"
  * @param statement the user statement, assumed to contain "you" followed by "me"
  * @return the transformed statement
  */
 private String transformYouMeStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  
  int psnOfYou = findKeyword (statement, "you", 0);
  int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
  
  String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
  return "What makes you think that I " + restOfStatement + " you?";
 }
 
 /**
  * Take a statement with "I <something> you" and transform it into 
  * "Why do you <something> me?"
  * @param statement the user statement, assumed to contain "I" followed by "you"
  * @return the transformed statement
  */
 private String transformIYouStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  
  int psnOfI = findKeyword (statement, "I", 0);
  int psnOfYou = findKeyword (statement, "you", psnOfI);
  
  String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
  return "Why do you " + restOfStatement + " me?";
 }
 

 
 
 /**
  * Search for one word in phrase.  The search is not case sensitive.
  * This method will check that the given goal is not a substring of a longer string
  * (so, for example, "I know" does not contain "no").  
  * @param statement the string to search
  * @param goal the string to search for
  * @param startPos the character of the string to begin the search at
  * @return the index of the first occurrence of goal in statement or -1 if it's not found
  */
 private int findKeyword(String statement, String goal, int startPos)
 {
  String phrase = statement.trim();
  //  The only change to incorporate the startPos is in the line below
  int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
  
  //  Refinement--make sure the goal isn't part of a word 
  while (psn >= 0) 
  {
   //  Find the string of length 1 before and after the word
   String before = " ", after = " "; 
   if (psn > 0)
   {
    before = phrase.substring (psn - 1, psn).toLowerCase();
   }
   if (psn + goal.length() < phrase.length())
   {
    after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
   }
   
   //  If before and after aren't letters, we've found the word
   if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
     && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
   {
    return psn;
   }
   
   //  The last position didn't work, so let's find the next, if there is one.
   psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
   
  }
  
  return -1;
 }
 
 /**
  * Search for one word in phrase.  The search is not case sensitive.
  * This method will check that the given goal is not a substring of a longer string
  * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
  * @param statement the string to search
  * @param goal the string to search for
  * @return the index of the first occurrence of goal in statement or -1 if it's not found
  */
 private int findKeyword(String statement, String goal)
 {
  return findKeyword (statement, goal, 0);
 }
 


 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 private String getRandomResponse ()
 {
  Random r = new Random ();
  return randomResponses [r.nextInt(randomResponses.length)];
 }
 
 private String [] randomResponses = {"Interesting, tell me more",
   "Hmmm.",
   "Do you really think so?",
   "You don't say.",
   "I don't understand.",
   "What do you mean?",
   "Can you expand on that?",
   "I think so too.",
   "I guess...",
   "Can you reword that?",
   "What's your social security number?",
   "Tell me about your day!"
   
 };
 
}
