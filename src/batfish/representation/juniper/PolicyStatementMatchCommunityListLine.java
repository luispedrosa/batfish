package batfish.representation.juniper;

public class PolicyStatementMatchCommunityListLine extends PolicyStatementMatchLine {

   private String _listName;

   public PolicyStatementMatchCommunityListLine(String listName) {
      _listName = listName;
   }

   @Override
   public MatchType getType() {
      return MatchType.COMMUNITY_LIST;
   }
   
   public String getListName() {
      return _listName;
   }

}