package generic.dao.interfaces.business;

import generic.dao.model.Action;

import java.util.List;

public interface ActionService {
    List<Action> getAllActions();
    Action getById(Integer id);
    Action create(Action a);
    Action update(Action a);
}
