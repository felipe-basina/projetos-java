package generic.dao.interfaces.business;

import generic.dao.model.ActionProfile;

public interface ActionProfileService {
    ActionProfile save(ActionProfile ap);
    ActionProfile update(ActionProfile ap);
}
