//package edu.unl.cse.csce361.voting_system.model;
//
//import javax.persistence.*;
//import java.util.Optional;
//
//@Entity
//public class VoterEntity implements Voter {
//    @Id
//    @GeneratedValue
//    private int tagNumber;
//
//    @Column
//    private String name;
//
//    @Column
//    private VoterType type;
//
//    @Column
//    private Sex sex;
//
//    @Column
//    private int massInKg;
//
//    @Column
//    private String species;
//    
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn
//    private LocationEntity location;                // depends on concretion for database purposes
//
//    @Column
//    private String reactionToPetting;
//
//    @Column
//    private String reactionToFeeding;
//
//    @Column
//    private String reactionToWatching;
//    
//    @Column
//    private Integer pricePerKg;
//    
//    @Column
//    private Integer hunger;
//    
//    @Column
//    private Integer stress;
//
//    public VoterEntity() {                                         // 0-argument constructor
//    }
//
//    public VoterEntity(String name, VoterType type, Sex sex,      // convenience constructor
//                        int massInKg, String species,
//                        String reactionToPetting, String reactionToFeeding,
//                        String reactionToWatching, Integer hunger,
//                        Integer stress, Integer pricePerKg) {
//        setName(name);
//        setType(type);
//        setSex(sex);
//        setMassInKg(massInKg);
//        setSpecies(species);
//        setReactionToPetting(reactionToPetting);
//        setReactionToFeeding(reactionToFeeding);
//        setReactionToWatching(reactionToWatching);
//        setHunger(hunger);
//        setStress(stress);
//        setPricePerKg(pricePerKg);
//    }
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn
//    private PettingZoo zoo;
//
//    @Override
//    public int getTagNumber() {
//        return tagNumber;
//    }
//
//    @SuppressWarnings("unused")
//    public void setTagNumber(int tagNumber) {
//        this.tagNumber = tagNumber;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public VoterType getType() {
//        return type;
//    }
//
//    public void setType(VoterType type) {
//        this.type = type;
//    }
//
//    public Sex getSex() {
//        return sex;
//    }
//
//    public void setSex(Sex sex) {
//        this.sex = sex;
//    }
//
//    @Override
//    public int getMassInKg() {
//        return massInKg;
//    }
//
//    public void setMassInKg(int massInKg) {
//        this.massInKg = massInKg;
//    }
//
//    @Override
//    public String getSpecies() {
//        return species;
//    }
//
//    public void setSpecies(String species) {
//        this.species = species;
//    }
//
//    @Override
//    public LocationEntity getLocation() {
//        return location;
//    }
//
//    public void setLocation(LocationEntity location) {
//        this.location = location;
//    }
//    
//    public Integer getHunger() {
//    	return hunger;
//    }
//    
//    public void setHunger(Integer hunger) {
//    	this.hunger = hunger;
//    }
//    
//    public Integer getStress() {
//    	return stress;
//    }
//    
//    public void setStress(Integer stress) {
//    	this.stress = stress;
//    }
//    
//    public Integer getPricePerKg() {
//    	return pricePerKg;
//    }
//    
//    public void setPricePerKg(Integer pricePerKg) {
//    	this.pricePerKg = pricePerKg;
//    }
//
//    public String getReactionToPetting() {
//        return reactionToPetting;
//    }
//
//    public void setReactionToPetting(String reactionToPetting) {
//        this.reactionToPetting = reactionToPetting;
//    }
//
//    public String getReactionToFeeding() {
//        return reactionToFeeding;
//    }
//
//    public void setReactionToFeeding(String reactionToFeeding) {
//        this.reactionToFeeding = reactionToFeeding;
//    }
//
//    public String getReactionToWatching() {
//        return reactionToWatching;
//    }
//
//    public void setReactionToWatching(String reactionToWatching) {
//        this.reactionToWatching = reactionToWatching;
//    }
//
//    public PettingZoo getZoo() {
//        return zoo;
//    }
//
//    public void setZoo(PettingZoo zoo) {
//        this.zoo = zoo;
//    }
//
//    @Override
//    public boolean isMale() {
//        return getSex() == Sex.MALE;
//    }
//
//    @Override
//    public boolean isFemale() {
//        return getSex() == Sex.FEMALE;
//    }
//
//    @Override
//    public boolean isCarnivore() {
//        return getType() == VoterType.CARNIVORE;
//    }
//
//    @Override
//    public boolean isHerbivore() {
//        return getType() == VoterType.HERBIVORE;
//    }
//
//    protected String getSubjectivePersonalPronoun() {
//        if (isFemale()) return "She";
//        if (isMale()) return "He";
//        return "It";
//    }
//
//    protected String getObjectivePersonalPronoun() {
//        if (isFemale()) return "Her";
//        if (isMale()) return "Him";
//        return "It";
//    }
//
//    @SuppressWarnings("unused")
//    protected String getPossessivePronoun() {
//        if (isMale()) return "His";
//        if (isFemale()) return "Her";
//        return "Its";
//    }
//
//    @Override
//    public String pet() {
//        String defaultResponse =
//                getSubjectivePersonalPronoun() + " lets you pet " + getObjectivePersonalPronoun().toLowerCase() + ".";
//        return Optional.ofNullable(getReactionToPetting()).orElse(defaultResponse);
//    }
//
//    @Override
//    public String feed() {
//        String defaultResponse = getSubjectivePersonalPronoun() + " eats.";
//        return Optional.ofNullable(getReactionToFeeding()).orElse(defaultResponse);
//    }
//
//    @Override
//    public String watch() {
//        String defaultResponse = getSubjectivePersonalPronoun() + " does what " + getSpecies() + "s do.";
//        return Optional.ofNullable(getReactionToWatching()).orElse(defaultResponse);
//    }
//
//    @Override
//    public void moveTo(Location newLocation) {
//        HibernateUtil.getSession().beginTransaction();
//        @SuppressWarnings("unused") LocationEntity oldLocation = location;
//        location = (LocationEntity) newLocation;
//        HibernateUtil.getSession().save(this);
//        HibernateUtil.getSession().getTransaction().commit();
//    }
//
//    @Override
//    public String toString() {
//        return getName() + " the " + getSpecies();
//    }
//
//    public enum VoterType {
//        UNKNOWN,    // default value
//        HERBIVORE,
//        CARNIVORE
//    }
//
//    public enum Sex {
//        UNKNOWN,    // default value
//        FEMALE,
//        MALE
//    }
//}
