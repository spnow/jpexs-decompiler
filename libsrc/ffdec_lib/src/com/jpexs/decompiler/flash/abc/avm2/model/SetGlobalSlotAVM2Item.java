/*
 *  Copyright (C) 2010-2016 JPEXS, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.jpexs.decompiler.flash.abc.avm2.model;

import com.jpexs.decompiler.flash.helpers.GraphTextWriter;
import com.jpexs.decompiler.graph.GraphPart;
import com.jpexs.decompiler.graph.GraphSourceItem;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.TypeItem;
import com.jpexs.decompiler.graph.model.LocalData;

/**
 *
 * @author JPEXS
 */
public class SetGlobalSlotAVM2Item extends AVM2Item {

    public int slotId;

    @Override
    public GraphPart getFirstPart() {
        return value.getFirstPart();
    }

    public SetGlobalSlotAVM2Item(GraphSourceItem instruction, GraphSourceItem lineStartIns, int slotId, GraphTargetItem value) {
        super(instruction, lineStartIns, PRECEDENCE_ASSIGMENT, value);
        this.slotId = slotId;
    }

    @Override
    public GraphTextWriter appendTo(GraphTextWriter writer, LocalData localData) throws InterruptedException {
        writer.append("setglobalslot");
        writer.spaceBeforeCallParenthesies(2);
        writer.append("(");
        writer.append(slotId).append(",");
        value.toString(writer, localData);
        return writer.append(")");
    }

    @Override
    public boolean hasSideEffect() {
        return true;
    }

    @Override
    public GraphTargetItem returnType() {
        return TypeItem.UNBOUNDED;
    }

    @Override
    public boolean hasReturnValue() {
        return false;
    }
}
