package batfish.representation.cisco;

import java.io.Serializable;

import batfish.representation.LineAction;

public class ExpandedCommunityListLine implements Serializable {

   private static final long serialVersionUID = 1L;

   private LineAction _action;
   private String _regex;

   public ExpandedCommunityListLine(LineAction action, String regex) {
      _action = action;
      _regex = regex;
   }

   public LineAction getAction() {
      return _action;
   }

   public String getRegex() {
      return _regex;
   }
}
