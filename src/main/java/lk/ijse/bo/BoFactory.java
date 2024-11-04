package lk.ijse.bo;

import lk.ijse.bo.impl.*;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }
    public static BoFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }
    public SuperBo getBo(BoTypes boType) {
            switch (boType) {
                case User :
                    return new UserBoImpl();
                case Student:
                    return new StudentBoImpl();
                case Course:
                    return new CourseBoImpl();
                case Registration:
                    return new RegistrationBoImpl();
                case Payment:
                    return new PaymentBoImpl();


            }
            return null;
    }
}
