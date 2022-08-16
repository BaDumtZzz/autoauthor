package ru.rsreu.autoauthor.command.client;

import ru.rsreu.autoauthor.command.*;
import ru.rsreu.autoauthor.command.admin.*;
import ru.rsreu.autoauthor.command.leader.*;

import java.util.HashSet;
import java.util.Set;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
            this.role = new HashSet<>();
            this.role.add("unloggedUser");
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
            this.role.add("member");
        }
    },
    TO_MAIN {
        {
            this.command = new ToMainCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
            this.role.add("member");
        }
    },
    TO_REGISTRATION{
        {
            this.command = new ToRegistrationCommand();
            this.role = new HashSet<>();
            this.role.add("unloggedUser");
        }
    },
    REGISTRATION{
        {
            this.command = new RegistrationCommand();
            this.role = new HashSet<>();
            this.role.add("unloggedUser");
        }
    },
    TO_PROFILE {
        {
            this.command = new ToProfileCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
            this.role.add("member");
        }
    },

    TO_LEADER{
        {
            this.command = new ToLeaderCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    TO_ADMIN {
        {
            this.command = new ToAdminCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
        }
    },
    RETRAIN {
        {
            this.command = new RetrainCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    TO_ADD_USER {
        {
            this.command = new ToAddUserCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
        }
    },
    TO_ADD_MEMBER {
        {
            this.command = new ToAddMemberCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    ADD_MEMBER {
        {
            this.command = new AddMemberCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
        }
    },
    TO_GROUP {
        {
            this.command = new ToGroupCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    TO_ADD_GROUP  {
        {
            this.command = new ToAddGroupCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    ADD_GROUP  {
        {
            this.command = new AddGroupCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    CREATE_GROUP  {
        {
            this.command = new CreateGroupCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    DELETE_GROUP {
        {
            this.command = new DeleteGroupCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    BLOCK_USER {
        {
            this.command = new BlockUserCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
        }
    },
    UNLOCK_USER {
        {
            this.command = new UnlockUserCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
        }
    },
    TO_IDENTIFICATION {
        {
            this.command = new ToIdentificationCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
            this.role.add("member");
        }
    },
    TO_ABOUT{
        {
            this.command = new ToAboutCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
            this.role.add("member");
        }
    },
    TO_USER{
        {
            this.command = new ToUserCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    KICK_USER{
        {
            this.command = new KickUserCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    TO_AUTHOR{
        {
            this.command = new ToAuthorCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    TO_ADD_AUTHOR {
        {
            this.command = new ToAddAuthorCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    ADD_AUTHOR {
        {
            this.command = new AddAuthorCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    DELETE_AUTHOR {
        {
            this.command = new DeleteAuthorCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    TO_ADD_FILE {
        {
            this.command = new ToAddFileCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    TO_FILE {
        {
            this.command = new ToFileCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    DELETE_FILE {
        {
            this.command = new DeleteFileCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
        }
    },
    UPLOAD_AUTHOR_FILE {
        {
            this.command = new UploadAuthorFileCommand();
            this.role = new HashSet<>();
            this.role.add("leader");
        }
    },
    UPLOAD_UNKNOWN_FILE {
        {
            this.command = new UploadUnknownFileCommand();
            this.role = new HashSet<>();
            this.role.add("admin");
            this.role.add("leader");
            this.role.add("member");
        }
    };

    ActionCommand command;
    Set<String> role;

    public ActionCommand getCurrentCommand() {
        return command;
    }
    public  Set<String> getRole() {
        return role;
    }
}
