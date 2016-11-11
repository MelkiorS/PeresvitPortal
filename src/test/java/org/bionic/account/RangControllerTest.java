/*
package org.bionic.account;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.bionic.config.WebAppConfigurationAware;
import org.bionic.controller.RangController;
import org.bionic.dao.RangRepository;
import org.bionic.entity.Rang;

import org.bionic.service.RangService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


public class RangControllerTest extends WebAppConfigurationAware{

    @InjectMocks
    RangController rangController;
    @Mock
    RangRepository rangRepository;
    @Mock
    private RangService rangServiceMock;

    @Test
    public void ShouldShowAllRangs() throws Exception{
        Rang rang1 = new Rang();
        rang1.setRangId(1L);
        rang1.setRangName("Level1");
        Rang rang2 = new Rang();
        rang2.setRangName("Level2");

        when(rangServiceMock.findAll()).thenReturn(Arrays.asList(rang1,rang2));

        mockMvc.perform(get("/admin/rang/"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/rang/allRangs"))
                .andExpect(forwardedUrl("WEB-INF/views/admin/rang/allRangs.html"))
                .andExpect(model().attribute("rangList", hasSize(2)))
                .andExpect(model().attribute("rangList", hasItem(
                        allOf(
                                hasProperty("rangId", is(1L)),
                                hasProperty("rangName", is("Level1"))
                        )
                )))
                .andExpect(model().attribute("rangList", hasItem(
                        allOf(
                                hasProperty("rangId", is(nullValue())),
                                hasProperty("rangName", is("Level2"))
                        )
                )));

        verify(rangServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(rangServiceMock);
    }
}

*/
