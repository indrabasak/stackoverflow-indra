package com.basaki.misc.type;

/**
 * {@code MedicalInformationTypeEnm} finds an enum type associated with a value
 * in  a String array.
 * <p>
 * Answers to stackoverflow questions:
 * <ol>
 * <li>https://stackoverflow.com/questions/46267302/find-enum-type-associated-with-a-value-in-string-array/46267963#46267963#46867865</li>
 * </ol>
 *
 * @author Indra Basak
 * @since 9/17/17
 */
@SuppressWarnings({"squid:S00115", "squid:S106"})
public enum MedicalInformationTypeEnm {

    foodAllergies("Food Allergies",
            new String[]{},
            new String[]{
                    "Milk", "Cheese", "Curd", "Tamarind", "Nuts", "Garlic", "Peanut",
                    "Soya", "Lemon", "Other Fruits", "Wheat", "Oats", "Peppers", "Gluten",
                    "Egg", "Meat", "Shellfish/Fish", "Synthetic Colouring", "Preserved Foods"}),

    drugAllergies("Drug Allergies",
            new String[]{"Oral Contraceptives", "Sulfa Drugs"},
            new String[]{
                    "Antibiotics", "Painkillers", "NSAIDS", "Sedatives", "Psychiatric Drugs",
                    "Local Anaesthetics (Xylocaine, Lignocaine)", "Cardiovascular Drugs",
                    "Vaccines", "Phenytoin(Eptoin)", "Carbamazepine(Tegretol)", "Penicillin", "Contrast Dyes"});


    private String name;
    private String[] drug;
    private String[] allergies;

    MedicalInformationTypeEnm(String name, String[] drug, String[] allergies) {
        this.name = name;
        this.drug = drug;
        this.allergies = allergies;
    }

    public static MedicalInformationTypeEnm fromAllergy(String allergy) {

        for (MedicalInformationTypeEnm info : values()) {
            for (String a : info.allergies) {
                if (a.equalsIgnoreCase(allergy)) {
                    return info;
                }
            }
        }

        throw new IllegalArgumentException("Allergy not found!");
    }


    public static void main(String[] args) {
        MedicalInformationTypeEnm info =
                MedicalInformationTypeEnm.fromAllergy("Garlic");
        System.out.println(info);

        info = MedicalInformationTypeEnm.fromAllergy("Penicillin");
        System.out.println(info);

        info = MedicalInformationTypeEnm.fromAllergy("test");
        System.out.println(info);
    }
}
