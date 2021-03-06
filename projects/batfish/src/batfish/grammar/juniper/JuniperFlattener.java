package batfish.grammar.juniper;

import java.util.ArrayList;
import java.util.List;

import batfish.grammar.juniper.JuniperParser.*;

public class JuniperFlattener extends JuniperParserBaseListener {

   private List<String> _currentBracketedWords;
   private List<String> _currentStatement;
   private String _flattenedConfigurationText;
   private boolean _inBrackets;
   private List<String> _setStatements;
   private List<List<String>> _stack;

   public JuniperFlattener() {
      _stack = new ArrayList<List<String>>();
      _setStatements = new ArrayList<String>();
   }

   @Override
   public void enterBracketed_clause(Bracketed_clauseContext ctx) {
      _currentBracketedWords = new ArrayList<String>();
      _inBrackets = true;
   }

   @Override
   public void enterStatement(StatementContext ctx) {
      _currentStatement = new ArrayList<String>();
      _stack.add(_currentStatement);
   }

   @Override
   public void exitBracketed_clause(Bracketed_clauseContext ctx) {
      _inBrackets = false;
   }

   @Override
   public void exitJuniper_configuration(Juniper_configurationContext ctx) {
      StringBuilder sb = new StringBuilder();
      sb.append("#\n");
      for (String setStatement : _setStatements) {
         sb.append(setStatement + "\n");
      }
      _flattenedConfigurationText = sb.toString();
   }

   @Override
   public void exitStatement(StatementContext ctx) {
      _stack.remove(_stack.size() - 1);
   }

   @Override
   public void exitTerminator(TerminatorContext ctx) {
      StringBuilder sb = new StringBuilder();
      sb.append("set");
      for (List<String> prefix : _stack) {
         for (String word : prefix) {
            sb.append(" " + word);
         }
      }
      String setStatementBase = sb.toString();
      if (_currentBracketedWords != null) {
         for (String bracketedWord : _currentBracketedWords) {
            String setStatement = setStatementBase + " " + bracketedWord;
            _setStatements.add(setStatement);
         }
         _currentBracketedWords = null;
      }
      else {
         _setStatements.add(setStatementBase);
      }
   }

   @Override
   public void exitWord(WordContext ctx) {
      String word = ctx.getText();
      if (_inBrackets) {
         _currentBracketedWords.add(word);
      }
      else {
         _currentStatement.add(word);
      }
   }

   public String getFlattenedConfigurationText() {
      return _flattenedConfigurationText;
   }

}
