'use client'

import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import 'react-tabs/style/react-tabs.css';
import CodeBlock from './CodeBlock';

export default function CodeTab({
  beforeExampleOneContents,
  afterExampleOneContents,
  beforeExampleTwoContents,
  afterExampleTwoContents
}: {
  beforeExampleOneContents: string[],
  afterExampleOneContents: string[],
  beforeExampleTwoContents: string[],
  afterExampleTwoContents: string[]
}) {

  return (
    <Tabs>
      <TabList>
        <Tab>Example 1</Tab>
        <Tab>Example 2</Tab>
      </TabList>

      <TabPanel>
        <Tabs>
          <TabList>
            <Tab>Before</Tab>
            <Tab>After</Tab>
          </TabList>
          <TabPanel><CodeBlock
            code={beforeExampleOneContents.join('\n')}
            language="kotlin"
          /></TabPanel>
          <TabPanel><CodeBlock
            code={afterExampleOneContents.join('\n')}
            language="kotlin"
          /></TabPanel>
        </Tabs>
      </TabPanel>

      <TabPanel>
        <Tabs>
          <TabList>
            <Tab>Before</Tab>
            <Tab>After</Tab>
          </TabList>
          <TabPanel><CodeBlock
            code={beforeExampleTwoContents.join('\n')}
            language="kotlin"
          /></TabPanel>
          <TabPanel><CodeBlock
            code={afterExampleTwoContents.join('\n')}
            language="kotlin"
          /></TabPanel>
        </Tabs>
      </TabPanel>
    </Tabs>
  );
};