<%@ include file="/taglib-imports.jspf" %>
<tiles:insert definition="main.layout">

    <tiles:put name="title" type="string">
        <bean:message key="choise.page.title"/>
    </tiles:put>

    <tiles:put name="style" type="string">
        <link rel="stylesheet" type="text/css" media="screen" href="<html:rewrite page="/layout/default-manageable.css"/>"></link>
        <link rel="stylesheet" type="text/css" media="all" href="<html:rewrite page="/layout/default-calendar.css"/>"/>
    </tiles:put>

    <tiles:put name="javascript" type="string">
        <script type="text/javascript" src="<html:rewrite action="/calendar"/>"></script>
        <script type="text/javascript">
        //<!--
            function setSelect(multi, form, name, value)
            {
                var select = form.elements[name];
                var options = select.options;

                // for browser compatibility's sake we go through the options ourselves
                for (var i=0; i<options.length; i++)
                {
                    if (multi)
                    {
                        // Array.indexOf is defined in Javascript 1.5, not before
                        options[i].selected = arrayContainsElement(value,options[i].value);
                    }
                    else
                    {
                        if (options[i].value == value)
                        {
                            select.selectedIndex = i;
                            break;
                        }
                    }

                }
            }

            function arrayContainsElement(array, element)
            {
              var containsElement = false;
              for (var i=0; i<array.length && !containsElement; i++) containsElement = (array[i] == element);
              return containsElement;
            }

            function setAction(crud) { document.forms['manageChoiseForm'].elements['crud'].value = crud; }

            function enableUpdate(enabled) { document.getElementById("updateButton").disabled = !enabled; }

            var selectionCount = 0;

            function verifyEnableDelete(checkbox)
            {
                if (checkbox.checked) selectionCount++; else selectionCount--;
                document.getElementById("deleteButton").disabled = (selectionCount < 1);
            }

            function clearFields(form)
            {
                form.reset();
                enableUpdate(false);
                setFields("","","",false,"","","[]",form);
            }

            function setFields(id,ordernum,pricewithtax,wrapping,purchase,item,deliveryAddressChoises,form)
            {
                form.elements["id"].value = id;
                form.elements["ordernum"].value = ordernum;
                form.elements["pricewithtax"].value = pricewithtax;
                form.elements["wrapping"].checked = wrapping;
                setSelect(false,form,"purchase",purchase);
                setSelect(false,form,"item",item);
                setSelect(true,form,"deliveryAddressChoises",deliveryAddressChoises.substring(1,deliveryAddressChoises.length-1).split(", "));
            }
        //-->
        </script>
    </tiles:put>

    <tiles:put name="body" type="string">

        <div>
            <h1><bean:message key="choise.page.title"/></h1>
        </div>

        <html:form styleId="manageChoiseForm" action="/Choise/Manage" method="post">
            <input type="hidden" name="crud" value=""/>
            <div id="criteria">
                <c:if test="${!empty manageableForm}">
                    <table>
                    <html:hidden name="manageableForm" property="id"/>
                        <tr>
                            <td><nobr><bean:message key="choise.ordernum"/> <div class="important">*</div></nobr></td>
                            <td>
                                <html:text name="manageableForm" property="ordernum" styleClass="criteriaField" styleId="ordernum"/>
                            </td>
                        </tr>
                        <tr>
                            <td><nobr><bean:message key="choise.pricewithtax"/> <div class="important">*</div></nobr></td>
                            <td>
                                <html:text name="manageableForm" property="pricewithtax" styleClass="criteriaField" styleId="pricewithtax"/>
                            </td>
                        </tr>
                        <tr>
                            <td><bean:message key="choise.wrapping"/></td>
                            <td>
                                <html:checkbox name="manageableForm" property="wrapping" styleClass="criteriaField" styleId="wrapping"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                    <nobr><html:link action="/Purchase/Manage"><bean:message key="choise.purchase"/></html:link> <div class="important">*</div></nobr>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty manageableForm.purchaseBackingList}">
                                        <select name="purchase" disabled="disabled"/>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="purchase">
                                            <option value=""><bean:message key="select.option.blank"/></option>
                                            <c:forEach var="valueLabel" items="${manageableForm.purchaseBackingList}">
                                                <c:choose>
                                                    <c:when test="${valueLabel[0] eq manageableForm.purchase}">
                                                        <option value="${valueLabel[0]}" selected="selected">${valueLabel[1]}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${valueLabel[0]}">${valueLabel[1]}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                    <nobr><html:link action="/Item/Manage"><bean:message key="choise.item"/></html:link> <div class="important">*</div></nobr>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty manageableForm.itemBackingList}">
                                        <select name="item" disabled="disabled"/>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="item">
                                            <option value=""><bean:message key="select.option.blank"/></option>
                                            <c:forEach var="valueLabel" items="${manageableForm.itemBackingList}">
                                                <c:choose>
                                                    <c:when test="${valueLabel[0] eq manageableForm.item}">
                                                        <option value="${valueLabel[0]}" selected="selected">${valueLabel[1]}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${valueLabel[0]}">${valueLabel[1]}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                    <html:link action="/DeliveryAddressChoise/Manage"><bean:message key="choise.delivery.address.choises"/></html:link>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty manageableForm.deliveryAddressChoisesBackingList}">
                                        <select name="deliveryAddressChoises" disabled="disabled"/>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="deliveryAddressChoises" multiple="multiple">
                                            <c:forEach var="valueLabel" items="${manageableForm.deliveryAddressChoisesBackingList}">
                        <option value="${valueLabel[0]}"<collections:contains item="${valueLabel}" array="${manageableForm.deliveryAddressChoises}"> selected="selected"</collections:contains>>${valueLabel[1]}</option>
                                            </c:forEach>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>

                    <input type="submit" id="readButton" class="button" value="<bean:message key="button.read"/>" onclick="this.form.elements['id'].value='';setAction('read');"/>
                    <input type="submit" id="createButton" class="button" value="<bean:message key="button.create"/>" onclick="setAction('create');"/>
                    <input type="submit" id="deleteButton" class="button" value="<bean:message key="button.delete"/>" disabled="disabled" onclick="setAction('delete');"/>
                    <input type="submit" id="updateButton" class="button" value="<bean:message key="button.update"/>" disabled="disabled" onclick="setAction('update');return validateUpdate();"/>
                    <input type="button" id="clearButton" class="button" value="<bean:message key="button.clear"/>" onclick="clearFields(this.form);"/>
                </c:if>

<div id="entitySwitcher">
    <nobr>
        <bean:message key="select.other.entity"/>
        <select onchange="document.location=this.options[this.selectedIndex].value+'?ref_Choise='+this.form.elements['id'].value;">
            <option value="<html:rewrite page="/Attachment/Manage.do"/>"><bean:message key="attachment"/></option>
            <option value="<html:rewrite page="/Carriage/Manage.do"/>"><bean:message key="carriage"/></option>
            <option value="<html:rewrite page="/Category/Manage.do"/>"><bean:message key="category"/></option>
            <option selected="selected" value="<html:rewrite page="/Choise/Manage.do"/>"><bean:message key="choise"/></option>
            <option value="<html:rewrite page="/DbFile/Manage.do"/>"><bean:message key="db.file"/></option>
            <option value="<html:rewrite page="/DeliveryAddress/Manage.do"/>"><bean:message key="delivery.address"/></option>
            <option value="<html:rewrite page="/DeliveryAddressChoise/Manage.do"/>"><bean:message key="delivery.address.choise"/></option>
            <option value="<html:rewrite page="/GiftCard/Manage.do"/>"><bean:message key="gift.card"/></option>
            <option value="<html:rewrite page="/Item/Manage.do"/>"><bean:message key="item"/><bean:message key="referencing.entity.marker"/></option>
            <option value="<html:rewrite page="/PaymentMethod/Manage.do"/>"><bean:message key="payment.method"/></option>
            <option value="<html:rewrite page="/Prefecture/Manage.do"/>"><bean:message key="prefecture"/></option>
            <option value="<html:rewrite page="/Product/Manage.do"/>"><bean:message key="product"/></option>
            <option value="<html:rewrite page="/PublicUser/Manage.do"/>"><bean:message key="public.user"/></option>
            <option value="<html:rewrite page="/Purchase/Manage.do"/>"><bean:message key="purchase"/><bean:message key="referencing.entity.marker"/></option>
            <option value="<html:rewrite page="/StaticData/Manage.do"/>"><bean:message key="static.data"/></option>
        </select>
    </nobr>
</div>

            </div>

            <div id="manageableList" class="table">
                <c:if test="${!empty manageableForm.manageableList}">
                    <display:table name="${manageableForm.manageableList}" id="row" requestURI="${pageContext.request.requestURI}"
                            requestURIcontext="false"
                            export="true" pagesize="15" sort="list">
                        <display:column media="html" sortable="false">
                            <nobr>
                                <input type="radio" name="_copy" onclick="enableUpdate(true);setFields('<formatting:escape language="javascript">${row.id}</formatting:escape>','<formatting:escape language="javascript">${row.ordernum}</formatting:escape>','<formatting:escape language="javascript">${row.pricewithtax}</formatting:escape>',${row.wrapping},'<formatting:escape language="javascript">${row.purchase}</formatting:escape>','<formatting:escape language="javascript">${row.item}</formatting:escape>','<formatting:escape language="javascript">${row.deliveryAddressChoises}</formatting:escape>',this.form);"/>
                                <input type="checkbox" name="selectedRows" value="${row.id}" onclick="verifyEnableDelete(this);"/>
                            </nobr>
                        </display:column>
                        <display:column media="xml csv excel pdf"
                            property="ordernum"
                            titleKey="choise.ordernum"/>
                        <display:column media="html"
                            headerClass="ordernum" paramId="ordernum" maxLength="36"
                            sortProperty="ordernum" sortable="true"
                            titleKey="choise.ordernum"><nobr><formatting:escape language="javascript,html">${row.ordernum}</formatting:escape></nobr></display:column>
                        <display:column media="xml csv excel pdf"
                            property="pricewithtax"
                            titleKey="choise.pricewithtax"/>
                        <display:column media="html"
                            headerClass="pricewithtax" paramId="pricewithtax" maxLength="36"
                            sortProperty="pricewithtax" sortable="true"
                            titleKey="choise.pricewithtax"><nobr><formatting:escape language="javascript,html">${row.pricewithtax}</formatting:escape></nobr></display:column>
                        <display:column media="html" headerClass="wrapping" titleKey="choise.wrapping">
                            <c:choose>
                                <c:when test="${row.wrapping}"><input type="checkbox" checked="checked" disabled="disabled"/></c:when>
                                <c:otherwise><input type="checkbox" disabled="disabled"/></c:otherwise>
                            </c:choose>
                        </display:column>
                        <display:column media="xml csv excel pdf"
                            property="wrapping" titleKey="choise.wrapping"/>
                        <display:column media="xml csv excel pdf"
                            property="purchase"
                            titleKey="choise.purchase"/>
                        <display:column media="html"
                            headerClass="purchase" paramId="purchase" maxLength="36"
                            sortProperty="purchase" sortable="true"
                            titleKey="choise.purchase"><nobr><formatting:escape language="javascript,html">${row.purchase}</formatting:escape></nobr></display:column>
                        <display:column media="xml csv excel pdf"
                            property="item"
                            titleKey="choise.item"/>
                        <display:column media="html"
                            headerClass="item" paramId="item" maxLength="36"
                            sortProperty="item" sortable="true"
                            titleKey="choise.item"><nobr><formatting:escape language="javascript,html">${row.item}</formatting:escape></nobr></display:column>
                        <display:column media="xml csv excel pdf"
                            property="deliveryAddressChoises"
                            titleKey="choise.delivery.address.choises"/>
                        <display:column media="html"
                            headerClass="deliveryAddressChoises" paramId="deliveryAddressChoises" maxLength="36"
                            sortProperty="deliveryAddressChoises" sortable="true"
                            titleKey="choise.delivery.address.choises"><nobr><formatting:escape language="javascript,html">${row.deliveryAddressChoises}</formatting:escape></nobr></display:column>
                    </display:table>
                </c:if>
            </div>

        </html:form>

        <div id="pageHelpSection">
            <blockquote>
                <a href="" id="pageHelp" style="display:inline;" onclick="openWindow('<html:rewrite action="/Choise/ManageHelp"/>','onlinehelp',true,false,760,540); return false;">
                    <bean:message key="online.help.href"/>
                </a>
                <html:img page="/layout/help.gif" style="display:inline;"/>
            </blockquote>
        </div>

    </tiles:put>

</tiles:insert>

