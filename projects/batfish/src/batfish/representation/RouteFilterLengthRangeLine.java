package batfish.representation;

import batfish.util.SubRange;
import batfish.util.Util;

public class RouteFilterLengthRangeLine extends RouteFilterLine {

   private static final long serialVersionUID = 1L;

   private SubRange _lengthRange;

   private Ip _prefix;

   private int _prefixLength;

   public RouteFilterLengthRangeLine(LineAction action, Ip prefix,
         int prefixLength, SubRange lengthRange) {
      super(action);
      _prefix = prefix;
      _prefixLength = prefixLength;
      _lengthRange = lengthRange;
   }

   @Override
   public String getIFString(int indentLevel) {
      String retString = Util.getIndentString(indentLevel)
            + String
                  .format(
                        "RouteFilterLengthRangeLine Prefix %s PrefixLength %s LengthRange %s Action %s",
                        _prefix, _prefixLength, _lengthRange, getAction());
      return retString;
   }

   public SubRange getLengthRange() {
      return _lengthRange;
   }

   public Ip getPrefix() {
      return _prefix;
   }

   public int getPrefixLength() {
      return _prefixLength;
   }

   @Override
   public RouteFilterLineType getType() {
      return RouteFilterLineType.LENGTH_RANGE;
   }

   @Override
   public boolean sameParseTree(RouteFilterLine line) {
      if (line.getType() != RouteFilterLineType.LENGTH_RANGE) {
         System.out.print("RouteFilterLenRangeLine:Type ");
         return false;
      }
      RouteFilterLengthRangeLine rhs = (RouteFilterLengthRangeLine) line;
      return (getAction() == rhs.getAction()) && (_prefix.equals(rhs._prefix))
            && (_prefixLength == rhs._prefixLength)
            && (_lengthRange.toString().equals(rhs._lengthRange.toString()));
   }

}
