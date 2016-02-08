package com.massivecraft.factions.zcore.persist;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.google.gson.Gson;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The PlayerEntityCollection is an EntityCollection with the extra features
 * a player skin usually requires.
 * <p>
 * This entity collection is not only creative. It even creates the instance for the player
 * when the player logs in to the server.
 * <p>
 * This way we can be sure that PlayerEntityCollection.get() will contain
 * all entities in PlayerEntityCollection.getOnline()
 */
public abstract class PlayerEntityCollection<E extends Entity> extends EntityCollection<E> {
    public PlayerEntityCollection(Class<E> entityClass, Collection<E> entities, Map<String, E> id2entity, File file, Gson gson) {
        super(entityClass, entities, id2entity, file, gson, true);
    }

    public E get(Player player) {
        return this.get(player.getName());
    }

    public Set<E> getOnline() {
        Set<E> entities = new HashSet<E>();
        for (Player player : Server.getInstance().getOnlinePlayers().values()) {
            entities.add(this.get(player));
        }
        return entities;
    }
}
