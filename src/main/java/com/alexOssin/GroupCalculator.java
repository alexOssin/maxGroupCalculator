package com.alexOssin;

import java.util.*;


public class GroupCalculator {
    private List<ConnectedGroup> groupList = new ArrayList<>();

    public GroupCalculator(final List<InputLine> inputLineList) {
        generateConnectedGroups(inputLineList);
    }

    public List<ConnectedGroup> getGroupList() {
        return groupList;
    }

    /**
     * The method runs through a list of groups comparing the "number of units" (not including CCUs ) in each group and outputs the maximum number.
     * @return max number or -1 if no group exists
     */
    public Integer calculateGroupWithMaximumNumberOfUnits(){
        OptionalInt maxGroup = groupList.stream()
                .mapToInt(ConnectedGroup::getNumberOfNodes)
                .max();
        outputGroupWithMaximumNumberOfUnits(maxGroup);
        return maxGroup.orElse(-1);


    }

    private void outputGroupWithMaximumNumberOfUnits(OptionalInt maxGroup){
        if(maxGroup.isPresent()){
            System.out.println("=============================");
            System.out.println("maximum number of connected units  is : "+maxGroup.getAsInt());
            System.out.println("=============================");

            //uncomment for debugging
            //System.out.println("see the details of the generated groups for an easier analysis\n"+groupList.toString());
        }

        if(maxGroup.isEmpty()){
            System.out.println("=============================");
            System.out.println("no connected groups were found ");
            System.out.println("=============================");

        }

    }



    /**
     * The algorithm runs through a list of InputLines , where each InputLine represents a line in an input file.
     *
     * if a unit name represented by a first field belongs to an existing connected group
     *      the method adds the all its visible units (including the CCUs) to the group.
     *
     * if a unit name represented by a first field doesn't exist in any  of the  groups the method  :
     *      - creates a new group ,
     *      - adds the unit represented by a first field and all its connected units (including the CCUs) to the group,
     *      - adds the new group to the list of the connected groups , represented by the groupList class field.
     *
     *  NOTE : since there is stated in the task description that I can assume that the input file is logically correct,
     *  no other group validation has been implemented.
     *
     */
    private void generateConnectedGroups(List<InputLine> inputLineList){
        inputLineList.forEach(line->{
            //check if a unit with specified first field  , exists in one of the groups
            if(containsByUnitName(line.getFirstField())){
                ConnectedGroup g = getGroupByUnitName(line.getFirstField());
                g.addAll(line.getConnectedUnits());//add all connected units of the line to the group
            }
            else{
                ConnectedGroup newGroup = new ConnectedGroup();
                newGroup.add(new NetworkUnit(line.getFirstField()));
                newGroup.addAll(line.getConnectedUnits());
                groupList.add(newGroup);
            }
        });

    }

    /**
     * Tests if there is a group that contains a unit with the specified unitName str
     * @param unitName
     * @return TRUE if there is a group that contains a unit with specified unitName ,else FALSE
     */
    private boolean containsByUnitName(String unitName) {
        return groupList.stream()
                .filter(group->group.containsByUnitName(unitName))
                .findAny().isPresent();
    }

    /**
     * Looks up a group that contains a unit with the specified unitName str
     * @param unitName
     * @return group with specified unitName or null if such doesn't exist in
     */
    private ConnectedGroup getGroupByUnitName(String unitName){
        return groupList.stream()
                .filter(group -> group.containsByUnitName(unitName))
                .findAny().orElse(null);
    }
}
