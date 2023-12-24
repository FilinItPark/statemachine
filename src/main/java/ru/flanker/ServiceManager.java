package ru.flanker;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

/**
 * @author 1ommy
 * @version 24.12.2023
 */
public class ServiceManager {
    private final Map<State, Service> methods;

    private final MainMenuService mainMenuService;
    private final AddNewFimService addNewFimService;
    private final ShowFilmsService showFilmsService;
    private final FindFilmsService findFilmsService;

    public ServiceManager() throws Exception {
        methods = new HashMap<>();

        DbConnection dbConnection = new DbConnection(
                SystemStringsStorage.DbUrl,
                SystemStringsStorage.DbUser,
                SystemStringsStorage.DbPassword
        );

        TableFilms tableFilms = new TableFilmsImpl(dbConnection.getConnection());
        DbManager dbManager = new DbManager(tableFilms);

        mainMenuService = new MainMenuService(dbManager);
        addNewFimService = new AddNewFimService(dbManager);
        showFilmsService = new ShowFilmsService(dbManager);
        findFilmsService = new FindFilmsService(dbManager);

        methods.put(State.CommandStart, mainMenuService::processCommandStart);
        methods.put(State.ClickInMenuMain, mainMenuService::processClickInMenuMain);

        methods.put(State.InputFilmNameInAddFilm, this::processUpdate);
        methods.put(State.InputFilmUrlInAddFilm, addNewFimService::processInputFilmUrlInAddFilm);
        methods.put(State.InputFilmTagsInAddFilm, addNewFimService::processInputFilmTagsInAddFilm);
        methods.put(State.ClickInAddFilm, addNewFimService::processClickBackToMenuMainInAddFilm);

        methods.put(State.ClickInShowFilms, showFilmsService::processClickInShowFilms);

        methods.put(State.InputSearchValueInFindFilms, findFilmsService::processInputSearchValueInFindFilms);
        methods.put(State.ClickInFindFilm, findFilmsService::processClickInFindFilm);
    }

    public SendMessage processUpdate(String textData, TransmittedData transmittedData) throws Exception {
        return methods.get(transmittedData.getState()).processUpdate(textData, transmittedData);
    }
}
