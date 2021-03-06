/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/

package org.ballerinalang.model.values;

import org.ballerinalang.model.types.BArrayType;
import org.ballerinalang.model.types.BType;
import org.ballerinalang.model.types.BTypes;

/**
 * Ballerina Value that encapsulates a Ballerina Type.
 *
 * @since 0.90
 */
public class BTypeValue implements BRefType<BType> {

    private BType typeValue;

    public BTypeValue() {
        this(null);
    }

    public BTypeValue(BType typeValue) {
        this.typeValue = typeValue;
    }

    @Override
    public BType value() {
        return typeValue;
    }

    @Override
    public String stringValue() {
        return null;
    }

    @Override
    public BType getType() {
        return BTypes.typeType;
    }

    @Override
    public BValue copy() {
        return new BTypeValue(typeValue);
    }

    public boolean equals(Object obj) {
        BTypeValue typeValue = (BTypeValue) obj;
        if ((typeValue.value() instanceof BArrayType) &&
                (this.value() instanceof BArrayType)) {
            BArrayType objArrayType = (BArrayType) typeValue.value();
            BArrayType thisArrayType = (BArrayType) this.value();
            if (objArrayType.getDimensions() != thisArrayType.getDimensions()) {
                return false;
            }
            return (new BTypeValue(thisArrayType.getElementType()))
                    .equals(new BTypeValue(objArrayType.getElementType()));
        } else {
            return typeValue.value() == this.value();
        }
    }

}
