package za.ac.sun.cs.hons.argyle.client.serialization.entities;

import za.ac.sun.cs.hons.argyle.client.serialization.GWTSerializable;

public abstract class IsEntity extends GWTSerializable implements HasID {
    @Override
    public abstract long getID();

}
