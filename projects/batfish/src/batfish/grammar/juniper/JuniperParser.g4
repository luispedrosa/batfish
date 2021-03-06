parser grammar JuniperParser;

options {
   superClass = 'batfish.grammar.BatfishParser';
   tokenVocab = JuniperLexer;
}

@header {
package batfish.grammar.juniper;
}

braced_clause
:
   OPEN_BRACE statement* CLOSE_BRACE
;

bracketed_clause
:
   OPEN_BRACKET word+ CLOSE_BRACKET
;

juniper_configuration
:
   statement+ EOF
;

statement
:
   word+
   (
      braced_clause
      |
      (
         bracketed_clause terminator
      )
      | terminator
   )
;

terminator
:
   SEMICOLON
;

word
:
   WORD
;