package org.codehaus.plexus.configuration.builder;

import junit.framework.TestCase;

import org.codehaus.plexus.configuration.builder.XmlPullConfigurationBuilder;
import org.codehaus.plexus.configuration.Configuration;

import java.io.StringReader;

/**
 * Generated by JUnitDoclet, a tool provided by ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org and www.objectfab.de for
 * informations about the tool, the licence and the authors.
 */
public class XmlPullConfigurationBuilderTest
    extends TestCase
{
    public XmlPullConfigurationBuilderTest( String name )
    {
        super( name );
    }

    public void testSimpleConfigurationBuilding()
        throws Exception
    {
        String s = "<conf><name>jason</name></conf>";

        XmlPullConfigurationBuilder cb = new XmlPullConfigurationBuilder();

        Configuration c = cb.parse( new StringReader( s ) );

        assertNotNull( c );

        assertEquals( "jason", c.getChild( "name" ).getValue() );
    }

    public void testMixedContentFailure()
        throws Exception
    {
        String s = "<conf>mixed<name>jason</name></conf>";

        XmlPullConfigurationBuilder cb = new XmlPullConfigurationBuilder();

        try
        {
            cb.parse( new StringReader( s ) );

            fail( "Exception should be thrown due to mixed content error." );
        }
        catch ( Exception e )
        {
            // do nothing
        }
    }

    public void testParserReuse()
        throws Exception
    {
        String s = "<conf><name>jason</name></conf>";

        XmlPullConfigurationBuilder cb = new XmlPullConfigurationBuilder();


        Configuration c = cb.parse( new StringReader( s ) );

        assertNotNull( c );

        assertEquals( "jason", c.getChild( "name" ).getValue() );


        s = "<conf><name>bob</name></conf>";

        c = cb.parse( new StringReader( s ) );

        assertNotNull( c );

        assertEquals( "bob", c.getChild( "name" ).getValue() );


        s = "<conf><name>pete</name></conf>";

        c = cb.parse( new StringReader( s ) );

        assertNotNull( c );

        assertEquals( "pete", c.getChild( "name" ).getValue() );


        s = "<conf><name>melissa</name></conf>";

        c = cb.parse( new StringReader( s ) );

        assertNotNull( c );

        assertEquals( "melissa", c.getChild( "name" ).getValue() );
    }

    public void testReaderClosure()
        throws Exception
    {
        String s = "<conf><name>jason</name></conf>";

        XmlPullConfigurationBuilder cb = new XmlPullConfigurationBuilder();

        CloseCheckStringReader reader = new CloseCheckStringReader( s );

        assertFalse( "reader closed before parsing", reader.isClosed() );

        cb.parse( reader );

        assertTrue( "reader not closed after parsing", reader.isClosed() );
    }
}