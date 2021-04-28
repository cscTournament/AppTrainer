package by.gourianova.apptrainer.action;

import by.gourianova.apptrainer.action.admin.ShowAdminPageAction;
import by.gourianova.apptrainer.action.admin.app.*;
import by.gourianova.apptrainer.action.admin.httpaddress.AddHttpAddressAction;
import by.gourianova.apptrainer.action.admin.httpaddress.ShowAllHttpAddressesAction;
import by.gourianova.apptrainer.action.admin.order.ShowAllOrdersAction;
import by.gourianova.apptrainer.action.admin.order.ShowUnclosedOrdersAction;
import by.gourianova.apptrainer.action.admin.order.ShowUserOrdersAction;
import by.gourianova.apptrainer.action.admin.role.AddRoleAction;
import by.gourianova.apptrainer.action.admin.role.DeleteRoleAction;
import by.gourianova.apptrainer.action.admin.user.*;
import by.gourianova.apptrainer.action.app.RentAppAction;
import by.gourianova.apptrainer.action.app.ReturnAppsAction;
import by.gourianova.apptrainer.action.app.ShowAllAppsByPageAction;
import by.gourianova.apptrainer.action.app.ShowAllUserAppsAction;
import by.gourianova.apptrainer.action.locale.ChangeLocaleAction;
import by.gourianova.apptrainer.action.user.*;


public enum ActionType {
    LOGIN(new LoginUserAction()),
    LOGOUT(new LogoutUserAction()),
    REGISTER(new RegisterUserAction()),

    RENT_APP(new RentAppAction()),
    RETURN_APP(new ReturnAppsAction()),

    ADD_MONEY(new AddMoneyAction()),
    ADD_TYPE(new AddTypeAction()),
    ADD_HTTP_ADDRESS(new AddHttpAddressAction()),
    ADD_ROLE(new AddRoleAction()),
    ADD_APP(new AddAppAction()),

    CHANGE_LOCALE(new ChangeLocaleAction()),
    CHANGE_USER(new ChangeUserAction()),
    CHANGE_USER_DATA(new ChangeUserDataAction()),
    CHANGE_TYPE(new ChangeTypeAction()),

    SHOW_ORDERS(new ShowOrdersAction()),
    SHOW_USER_ORDERS(new ShowUserOrdersAction()),
    SHOW_ALL_USER_APPS(new ShowAllUserAppsAction()),
    SHOW_ALL_APPS(new ShowAllAppsAction()),

    SHOW_ALL_USERS(new ShowAllUserAction()),
    SHOW_ALL_TYPES(new ShowAllTypesAction()),
    SHOW_ALL_ROLES(new ShowAllRoleAction()),
    SHOW_ALL_ORDERS(new ShowAllOrdersAction()),
    SHOW_ALL_HTTP_ADDRESSES(new ShowAllHttpAddressesAction()),
    SHOW_UNCLOSED_ORDERS(new ShowUnclosedOrdersAction()),
    SHOW_ADMIN_PAGE(new ShowAdminPageAction()),
    SHOW_APPS_PAGE(new ShowAllAppsByPageAction()),
    SHOW_DELETE_USER_PAGE(new ShowDeleteUserPageAction()),

    FIND_USER(new FindUserAction()),

    DELETE_USER(new DeleteUserAction()),
    DELETE_ROLE(new DeleteRoleAction()),

    GET_USER_DATA(new GetUserDataAction()),
    GET_APP_DATA(new GetAppDataAction()),
    GET_TYPE_DATA(new GetTypeDataAction());


    Action action;

    Action getAction() {
        return action;
    }

    ActionType(Action action) {
        this.action = action;
    }

}
